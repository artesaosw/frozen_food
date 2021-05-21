package com.capgemini.engineering.ddd.frozen_food.menu.infra.controller;

import com.capgemini.engineering.ddd.frozen_food._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.OrderID;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.service.MantainOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@Validated
public class OrderController {

    @Autowired
    private MantainOrders orderService;

    @GetMapping(path = "/stock/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOrderById(@PathVariable @Valid String id) throws NonExistentEntityException {
        try {
            OrderID orderID = Identificator.newInstance(OrderID.class, id);
            var stockOrder = orderService.getOrderById(orderID);
            return ResponseEntity.ok(stockOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e));
        }
    }

}

