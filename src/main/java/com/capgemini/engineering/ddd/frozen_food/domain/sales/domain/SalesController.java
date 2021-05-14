package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain;


import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.ProductionOrder;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository.CustomerRepository;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository.OrderRepository;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository.ProductRepository;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository.ProductionOrderRepository;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.service.CustomerService;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.service.OrderService;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.service.ProductService;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.service.ProductionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController("/sales")
public class SalesController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductionOrderService productionOrderService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@NotBlank @PathVariable String id) {
        return this.customerService.findById(id);
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return this.customerService.findAllCustomers();
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@NotNull @Valid @RequestBody Customer customer) {
        return this.customerService.updateCustomer(customer);
    }

    @PostMapping("/customers")
    public Customer createNewCustomer(@NotNull @Valid @RequestBody Customer newCustomer) {
        return this.customerService.createNewCustomer(newCustomer);
    }


}
