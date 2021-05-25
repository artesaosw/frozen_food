package com.capgemini.engineering.ddd.frozen_food.sales.infra.controller;


import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Order;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Product;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.exception.*;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.valueObject.NIF;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.service.CustomerService;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.service.OrderService;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.service.ProductService;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.service.ProductionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.NoSuchElementException;

@RestController()
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductionOrderService productionOrderService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@NotBlank @PathVariable String id) {
        return this.customerService.findById(id);
    }

    @GetMapping("/customers/exists/{id}")
    public boolean existsCustomerByBillingInfo(@NotNull @PathVariable NIF nif) {
        return this.customerService.getCustomerRepository().existsCustomerByNif(nif);
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return this.customerService.findAllCustomers();
    }

    @PutMapping("/customers")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody Customer customer) {

        try {
            return new ResponseEntity<Customer>(this.customerService.updateCustomer(customer), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>("Customer does not exist in the database.", HttpStatus.BAD_REQUEST);
        } catch (DomainIndependentIDMismatchException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<?> createNewCustomer(@Valid @RequestBody Customer newCustomer) {

        try {
            return new ResponseEntity<Customer>(this.customerService.createNewCustomer(newCustomer),
                HttpStatus.CREATED);
        }
        catch(InfoAlreadyExistsException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/orders/{id}")
    public Order getOrderById(@NotBlank @PathVariable String id) {
        return this.orderService.getOrderById(id);
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return this.orderService.getAllOrders();
    }

    @PostMapping("/orders")
    public ResponseEntity<?> registerNewOrder(@Valid @RequestBody Order order) {

        if (order.validateOrderProducts()) {
            return new ResponseEntity<String>("Product list mismatch. Either there are repeat products" +
                    "or the lists' sizes don't match.", HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<Order>(this.orderService.registerNewOrder(order), HttpStatus.CREATED);
        } catch (OrderAlreadyExistsException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (CustomerDoesNotExistException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (CloneNotSupportedException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> cancelOrder(@NotBlank String id) {

        try {
            this.orderService.cancelOrder(id);
            return new ResponseEntity<String>("Order with id " + id + " was successfully cancelled.",
                    HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>("Order with id " + id + " doesn't exist.",
                    HttpStatus.BAD_REQUEST);
        } catch (OrderAlreadyCancelled e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (CloneNotSupportedException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
