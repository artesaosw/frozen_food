package com.capgemini.engineering.ddd.frozen_food.menu.infra.controller;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ChefOrderID;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.OrderID;
import com.capgemini.engineering.ddd.frozen_food._shared.utils.Error;
import com.capgemini.engineering.ddd.frozen_food._shared.utils.Message;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Order;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.service.MantainOrders;
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
@RequestMapping("/menu/order")
@Validated
public class OrderController {

    static final String NO_DATA_ERROR_MSG = "NO DATA RECEIVED ERROR";
    static final String ADD_SUCCESS_MSG = "Stock Order added successfully!";
    static final String UPDATE_SUCCESS_MSG = "Stock Order updated successfully!";
    static final String DELETE_SUCCESS_MSG = "Stock Order deleted successfully!";
    static final String MISSING_PARAMETER_ERROR_MSG = "MISSING PARAMETER ERROR";
    static final String MISSING_PARAMETER_VALUE_ERROR_MSG = "MISSING PARAMETER VALUE ERROR";


    @Autowired
    private MantainOrders orderService;

    public OrderController() {
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<?> handleInvalidData(MethodArgumentNotValidException ex) {
        List<String> messages = ex.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(messages);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<?> handleNoData(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new Error(new Exception(NO_DATA_ERROR_MSG)));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ResponseEntity<?> handleNoData(MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest().body(new Error(new Exception(MISSING_PARAMETER_ERROR_MSG)));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<?> handleNoData(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().body(new Error(new Exception(MISSING_PARAMETER_VALUE_ERROR_MSG)));
    }

    @GetMapping(path = "/stock/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOrderById(@PathVariable @Valid String id) throws NonExistentEntityException {
        try {
            ChefOrderID chefOrderID = Identificator.newInstance(OrderID.class, id);
            var stockOrder = orderService.getOrderById(chefOrderID);
            return ResponseEntity.ok(stockOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @GetMapping(path = "/stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllOrders() {
        try {
            return ResponseEntity.ok(orderService.getAllOrders());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @PostMapping(path = "/stock")
    public ResponseEntity<?> addOrder(@RequestBody @Valid @NotNull Order order) {
        try {
            orderService.registerNew(order);
            return ResponseEntity.created(URI.create("/stock/" + order.getId())).body(new Message(ADD_SUCCESS_MSG));
        } catch (DuplicatedEntityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @PutMapping(path = "/stock", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateOrder(@RequestBody @Valid @NotNull Order order) {
        try {
            orderService.updateOrder(order);
            return ResponseEntity.ok(new Message(UPDATE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

    @DeleteMapping(path = "/chef/{id}")
    public ResponseEntity<?> deleteChefOrder(@PathVariable @Valid String id) {
        try {
           ChefOrderID chefOrderID = Identificator.newInstance(ChefOrderID.class, id);
            orderService.deleteOrder(chefOrderID);
            return ResponseEntity.ok(new Message(DELETE_SUCCESS_MSG));
        } catch (NonExistentEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }
}