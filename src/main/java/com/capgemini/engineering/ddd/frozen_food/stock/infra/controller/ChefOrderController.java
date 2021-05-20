package com.capgemini.engineering.ddd.frozen_food.stock.infra.controller;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ChefOrderID;
import com.capgemini.engineering.ddd.frozen_food._shared.stock.dto.ErrorDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.stock.dto.MessageDTO;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ChefOrder;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.service.ChefOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    static final String NO_DATA_ERROR_MSG = "NO DATA RECEIVED ERROR";
    static final String ADD_SUCCESS_MSG = "Chef Order added successfully!";
    static final String UPDATE_SUCCESS_MSG = "Chef Order updated successfully!";
    static final String DELETE_SUCCESS_MSG = "Chef Order deleted successfully!";
    static final String MISSING_PARAMETER_ERROR_MSG = "MISSING PARAMETER ERROR";
    static final String MISSING_PARAMETER_VALUE_ERROR_MSG = "MISSING PARAMETER VALUE ERROR";

    @Autowired
    private ChefOrderService chefOrderService;

    public ChefOrderController() {
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<?> handleInvalidData(MethodArgumentNotValidException ex) {
        List<String> messages = ex.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(messages);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<?> handleNoData(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new ErrorDTO(new Exception(NO_DATA_ERROR_MSG)));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ResponseEntity<?> handleNoData(MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest().body(new ErrorDTO(new Exception(MISSING_PARAMETER_ERROR_MSG)));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<?> handleNoData(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().body(new ErrorDTO(new Exception(MISSING_PARAMETER_VALUE_ERROR_MSG)));
    }

    @GetMapping(path = "/chef/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getChefOrderById(@PathVariable @Valid String id) {
        try {
            String jsonString = String.format("{\"id\":\"%s\"}", id);
            ObjectMapper mapper = new ObjectMapper();
            var supplierID = mapper.readValue(jsonString, ChefOrderID.class);
            var chefOrder = chefOrderService.getChefOrderByChefOrderID(supplierID);
            return ResponseEntity.ok(chefOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @GetMapping(path = "/chef", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllChefOrders() {
        try {
            return ResponseEntity.ok(chefOrderService.getAllChefOrders());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @GetMapping(path = "/chef/delivered", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllDeliveredChefOrders() {
        try {
            return ResponseEntity.ok(chefOrderService.getAllChefOrdersByOrderStatus(OrderStatus.DELIVERED));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @GetMapping(path = "/chef/undelivered", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUndeliveredChefOrders() {
        try {
            return ResponseEntity.ok(chefOrderService.getAllChefOrdersByOrderStatus(OrderStatus.UNDELIVERED));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @GetMapping(path = "/chef/canceled", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCanceledChefOrders() {
        try {
            return ResponseEntity.ok(chefOrderService.getAllChefOrdersByOrderStatus(OrderStatus.CANCELED));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @PostMapping(path = "/chef")
    public ResponseEntity<?> addChefOrder(@RequestBody @Valid @NotNull ChefOrder chefOrder) {
        try {
            chefOrderService.registerNewChefOrder(chefOrder);
            return ResponseEntity.created(URI.create("/chef/" + chefOrder.getId())).body(new MessageDTO(ADD_SUCCESS_MSG));
        } catch (DuplicatedEntityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @PutMapping(path = "/chef", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateChefOrder(@RequestBody @Valid @NotNull ChefOrder chefOrder) {
        try {
            chefOrderService.updateChefOrder(chefOrder);
            return ResponseEntity.ok(new MessageDTO(UPDATE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @PutMapping(path = "/chef/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateChefOrderStatus(@PathVariable @Valid @NotNull String id, @RequestParam @Valid @NotNull OrderStatus orderStatus) {
        try {
            String jsonString = String.format("{\"id\":\"%s\"}", id);
            ObjectMapper mapper = new ObjectMapper();
            var supplierID = mapper.readValue(jsonString, ChefOrderID.class);
            chefOrderService.updateChefOrderStatus(supplierID, orderStatus);
            return ResponseEntity.ok(new MessageDTO(UPDATE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }

    @DeleteMapping(path = "/chef/{id}")
    public ResponseEntity<?> deleteChefOrder(@PathVariable @Valid ChefOrderID id) {
        try {
            String jsonString = String.format("{\"id\":\"%s\"}", id);
            ObjectMapper mapper = new ObjectMapper();
            var supplierID = mapper.readValue(jsonString, ChefOrderID.class);
            chefOrderService.deleteChefOrder(supplierID);
            return ResponseEntity.ok(new MessageDTO(DELETE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e));
        }
    }
}
