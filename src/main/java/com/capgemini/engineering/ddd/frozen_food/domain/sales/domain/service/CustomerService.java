package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.service;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository.CustomerRepository;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository.Customers;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CustomerService implements DomainServices {

    @Autowired
    private CustomerRepository customerRepository;

    private Customers customers() {
        return Domain.customers();
    }

    public void createNewCustomer (@NotBlank String name, @NotBlank String address,
                                   @NotBlank String billingInfo) {

        if (customerRepository.existsCustomerByBillingInfo(billingInfo)) {
            throw new IllegalArgumentException("A customer with the same billing information already exists.");
        }

        Customer newCustomer = new Customer(name, address, billingInfo);

        //persis new customer into the database
        customerRepository.save(newCustomer);
    }

    //so we can access all the nifty crud methods spring magically generates
    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }
}
