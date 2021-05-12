package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.service;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository.CustomerRepository;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CustomerService implements DomainServices {

    @Autowired
    private CustomerRepository customerRepository;

    private Customers customers() {
        return Domain.customers();
    }

    public ResponseEntity<?> createNewCustomer (@NotBlank String name, @NotBlank String address,
                                                     @NotBlank String billingInfo) {

        // can't have two customers with the same billing info
        if (customerRepository.existsCustomerByBillingInfo(billingInfo)) {
            return new ResponseEntity<String>("A customer with the same billing information already exists.",
                    HttpStatus.BAD_REQUEST);
        }

        Customer newCustomer = new Customer(name, address, billingInfo);

        //persis new customer into the database
        return new ResponseEntity<Customer>(this.customerRepository.save(newCustomer),
                HttpStatus.OK);
    }

    //so we can access all the nifty crud methods spring magically generates
    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }
}
