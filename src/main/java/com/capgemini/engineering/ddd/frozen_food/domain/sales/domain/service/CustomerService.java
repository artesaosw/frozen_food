package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.service;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.event.CustomerUpdatedEventPublisher;
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

    public ResponseEntity<String> disableCustomer(@NotBlank String id) {

        try {
            Customer customer = this.customerRepository.findById(id).get();

            //first check if customer is already disabled
            if(customer.isActivated()) {
                customer.setActivated(false);

                CustomerUpdatedEventPublisher eventPublisher = new CustomerUpdatedEventPublisher();
                eventPublisher.publishEvent(this.customerRepository.save(customer));

                return new ResponseEntity<String>("Customer with ID " + id + " has been disabled.", HttpStatus.OK);
            }

            return new ResponseEntity<String>("Customer with ID " + id + " is already disabled.", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<String>("An error has occurred: " + e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> enableCustomer(@NotBlank String id) {

        try {
            Customer customer = this.customerRepository.findById(id).get();

            //first check if customer is already enabled
            if(!customer.isActivated()) {
                customer.setActivated(true);

                CustomerUpdatedEventPublisher eventPublisher = new CustomerUpdatedEventPublisher();
                eventPublisher.publishEvent(this.customerRepository.save(customer));

                return new ResponseEntity<String>("Customer with ID " + id + " has been enabled.", HttpStatus.OK);
            }

            return new ResponseEntity<String>("Customer with ID " + id + " is already enabled.", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<String>("An error has occurred: " + e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    //so we can access all the nifty crud methods spring magically generates
    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }
}
