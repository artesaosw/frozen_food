package com.capgemini.engineering.ddd.frozen_food.sales.infra.controller;


import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Order;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.ProductionOrder;
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
    public ResponseEntity<?> getCustomerById(@Valid @NotBlank @PathVariable String id) {

        try {
            return new ResponseEntity<Customer>(this.customerService.findById(id) , HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>("Customer with id " + id + " doesn't exist.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/customers/exists/{id}")
    public boolean existsCustomerByBillingInfo(@Valid @NotNull @PathVariable NIF nif) {
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
    public ResponseEntity<?> getOrderById(@Valid @NotBlank @PathVariable String id) {

        try {
            return new ResponseEntity<Order>(this.orderService.getOrderById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>("Order with the id " + id + " not found.",
                    HttpStatus.OK);
        }

    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return this.orderService.getAllOrders();
    }

    @PostMapping("/orders")
    public ResponseEntity<?> registerNewOrder(@Valid @RequestBody Order order) {

        if (!order.validateOrderProducts()) {
            return new ResponseEntity<String>("Product list mismatch. Either there are repeat products" +
                    " or the lists' sizes don't match or are empty.", HttpStatus.BAD_REQUEST);
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

    @PutMapping("/orders/{id}/cancel")
    public ResponseEntity<String> cancelOrder(@Valid @NotBlank @PathVariable String id) {

        System.out.println(id);

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

    @GetMapping("/production_orders")
    public List<ProductionOrder> getAllProductionOrders() {
        return this.productionOrderService.findAll();
    }

    @GetMapping("/production_orders/{id}")
    public ResponseEntity<?> getProductionOrderById(@Valid @NotBlank @PathVariable String id) {

        try {
            return new ResponseEntity<ProductionOrder>(this.productionOrderService.findById(id),
                    HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>("Production order with the id " + id + " not found.",
                    HttpStatus.OK);
        }
    }

    @PostMapping("/production_orders")
    public ResponseEntity<?> createNewOrder(@Valid @NotNull @RequestBody ProductionOrder pOrder) {

        if(pOrder.getId() == null) {
            return new ResponseEntity<ProductionOrder>(this.productionOrderService.createNewProductionOrder(pOrder),
                    HttpStatus.CREATED);
        }

        return new ResponseEntity<String>("Production order to persist can't have an ID.",
                HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/production_orders/{id}/delete")
    public ResponseEntity<String> deleteProductionOrder(@Valid @NotBlank @PathVariable String id) {

        try {
            this.productionOrderService.deleteProductionOrder(id);
            return new ResponseEntity<String>("Production order with id " + id + " successfully deleted.",
                    HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>("Production order with id " + id + " does not exist.",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/production_orders/")
    public ResponseEntity<String> updateProductionOrder(@Valid @NotBlank @RequestBody ProductionOrder pOrder) {

        try {
            this.productionOrderService.deleteProductionOrder(pOrder.getId());
            return new ResponseEntity<String>("Production order with id " + pOrder.getId() + " successfully deleted.",
                    HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>("Production order with id " + pOrder.getId() + " does not exist.",
                    HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
