package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain;


import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.exception.BillingInfoAlreadyExistsException;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController()
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private CustomerService customerService;

//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private ProductionOrderService productionOrderService;
//
//    @Autowired
//    private OrderService orderService;

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@NotBlank @PathVariable String id) {
        return this.customerService.findById(id);
    }

    @GetMapping("/customers/exists/{id}")
    public boolean existsCustomerByBillingInfo(@NotBlank @PathVariable String billingInfo) {
        return this.customerService.getCustomerRepository().existsCustomerByBillingInfo(billingInfo);
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
    public ResponseEntity<?> createNewCustomer(@NotNull @Valid @RequestBody Customer newCustomer) {

        try {
            return new ResponseEntity<Customer>(this.customerService.createNewCustomer(newCustomer),
                HttpStatus.CREATED);
        }
        catch(BillingInfoAlreadyExistsException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


}
