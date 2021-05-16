package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.controller;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.ChefOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.ChefOrder;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.exception.DuplicatedKeyException;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.exception.NonExistentIngredientException;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.service.ChefOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@Validated
public class ChefOrderController {

    static final String GENERAL_ERROR_MSG = "INTERNAL_ERROR";
    static final String NO_DATA_ERROR_MSG = "NO_DATA_RECEIVED_ERROR";
    static final String DUPLICATED_ORDER_ERROR_MSG = "DUPLICATED_ORDER_ERROR";
    static final String ADD_SUCCESS_MSG = "ADD_SUCCESS";
    static final String UPDATE_SUCCESS_MSG = "UPDATE_SUCCESS";
    static final String DELETE_SUCCESS_MSG = "DELETE_SUCCESS";
    static final String ORDER_NOT_FOUND_ERROR_MSG = "ORDER_NOT_FOUND";
    static final String MISSING_PARAMETER_ERROR_MSG = "MISSING_PARAMETER_ERROR";
    static final String MISSING_PARAMETER_VALUE_ERROR_MSG = "MISSING_PARAMETER_VALUE_ERROR";

    @Autowired
    private ChefOrdersService chefOrdersService;

    public ChefOrderController() {
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<?> handleInvalidData(MethodArgumentNotValidException ex) {
        List<String> messages = ex.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(messages);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<?> handleNoData(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body(NO_DATA_ERROR_MSG);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ResponseEntity<?> handleNoData(MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body(MISSING_PARAMETER_ERROR_MSG);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<?> handleNoData(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body(MISSING_PARAMETER_VALUE_ERROR_MSG);
    }

    @GetMapping(path = "/chef/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> getChefOrderById(@PathVariable @Valid ChefOrderID id) {
        try {
            var chefOrder = chefOrdersService.getChefOrderById(id);
            return ResponseEntity.ok(chefOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @GetMapping(path = "/chef", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> getAllChefOrders() {
        try {
            return ResponseEntity.ok(chefOrdersService.getAllChefOrders());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @GetMapping(path = "/chef/delivered", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> getAllDeliveredChefOrders() {
        try {
            return ResponseEntity.ok(chefOrdersService.getAllChefOrdersByOrderStatus(OrderStatus.DELIVERED));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @GetMapping(path = "/chef/undelivered", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> getAllUndeliveredChefOrders() {
        try {
            return ResponseEntity.ok(chefOrdersService.getAllChefOrdersByOrderStatus(OrderStatus.UNDELIVERED));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @GetMapping(path = "/chef/canceled", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> getAllCanceledChefOrders() {
        try {
            return ResponseEntity.ok(chefOrdersService.getAllChefOrdersByOrderStatus(OrderStatus.CANCELED));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @PostMapping(path = "/chef", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> addChefOrder(@RequestBody @Valid @NotNull ChefOrder chefOrder) {
        try {
            chefOrdersService.registerNewChefOrder(chefOrder);
            return ResponseEntity.created(URI.create("/chef/" + chefOrder.getId())).contentType(MediaType.TEXT_PLAIN).body(ADD_SUCCESS_MSG);
        } catch (DuplicatedKeyException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(DUPLICATED_ORDER_ERROR_MSG);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @PutMapping(path = "/chef", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> updateChefOrder(@RequestBody @Valid @NotNull ChefOrder chefOrder) {
        try {
            chefOrdersService.updateChefOrder(chefOrder);
            return ResponseEntity.ok(UPDATE_SUCCESS_MSG);
        } catch (NonExistentIngredientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(ORDER_NOT_FOUND_ERROR_MSG);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @PutMapping(path = "/chef/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> updateChefOrderStatus(@PathVariable @Valid @NotNull ChefOrderID chefOrderID, @RequestParam @Valid @NotNull OrderStatus orderStatus) {
        try {
            chefOrdersService.updateChefOrderStatus(chefOrderID, orderStatus);
            return ResponseEntity.ok(UPDATE_SUCCESS_MSG);
        } catch (NonExistentIngredientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(ORDER_NOT_FOUND_ERROR_MSG);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }

    @DeleteMapping(path = "/chef/{id}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> deleteChefOrder(@PathVariable @Valid ChefOrderID id) {
        try {
            chefOrdersService.deleteChefOrder(id);
            return ResponseEntity.ok(DELETE_SUCCESS_MSG);
        } catch (NonExistentIngredientException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(ORDER_NOT_FOUND_ERROR_MSG);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(GENERAL_ERROR_MSG);
        }
    }
}
