package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.service;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.event.CustomerRegisteredEventPublisher;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.event.CustomerUpdatedEventPublisher;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.exception.BillingInfoAlreadyExistsException;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository.CustomerRepository;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository.Customers;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CustomerServiceImpl implements DomainServices, CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private Customers customers() {
        return Domain.customers();
    }

    public Customer createNewCustomer (Customer customer) {

        // can't have two customers with the same billing info
        // IS THIS CORRECT ??????????
        if (customerRepository.existsCustomerByBillingInfo(customer.getBillingInfo())) {
            throw new BillingInfoAlreadyExistsException("A customer with the same billing information already exists.");
        }

        //persist new customer into the database and issue an event
        customer = this.customerRepository.save(customer);

        CustomerRegisteredEventPublisher eventPublisher = new CustomerRegisteredEventPublisher();
        eventPublisher.publishEvent(customer);

        return customer;
    }

    public Customer updateCustomer(Customer customer) {
        //update the customer and issue an event
        customer = this.customerRepository.save(customer);

        CustomerUpdatedEventPublisher eventPublisher = new CustomerUpdatedEventPublisher();
        eventPublisher.publishEvent(customer);

        return customer;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findById(String id) {
        return this.customerRepository.findById(id).get();
    }

    @Override
    public void deleteCustomer(String id) {
        this.customerRepository.deleteById(id);
    }

    /*
    Disables customer with the ID passed and issues a CustomerUpdatedEvent.
     */
    public void disableCustomer(@NotBlank String id) {

//        try {
//            Customer customer = this.customerRepository.findById(id).get();
//
//            //first check if customer is already disabled
//            if(customer.isActivated()) {
//                customer.setActivated(false);
//
//                CustomerUpdatedEventPublisher eventPublisher = new CustomerUpdatedEventPublisher();
//                eventPublisher.publishEvent(this.customerRepository.save(customer));
//
//                return new ResponseEntity<String>("Customer with ID " + id + " has been disabled.", HttpStatus.OK);
//            }
//
//            return new ResponseEntity<String>("Customer with ID " + id + " is already disabled.", HttpStatus.OK);
//        }
//        catch (Exception e) {
//            return new ResponseEntity<String>("An error has occurred: " + e.toString(), HttpStatus.BAD_REQUEST);
//        }

        Customer customer = this.customerRepository.findById(id).get();

        //first check if customer is already disabled
        if(customer.isActivated()) {
            customer.setActivated(false);

            CustomerUpdatedEventPublisher eventPublisher = new CustomerUpdatedEventPublisher();
            eventPublisher.publishEvent(this.customerRepository.save(customer));

        }
    }

    /*
    Enables customer with the ID passed and issues a CustomerUpdatedEvent.
     */
    public void enableCustomer(@NotBlank String id) {

//        try {
//            Customer customer = this.customerRepository.findById(id).get();
//
//            //first check if customer is already enabled
//            if(!customer.isActivated()) {
//                customer.setActivated(true);
//
//                CustomerUpdatedEventPublisher eventPublisher = new CustomerUpdatedEventPublisher();
//                eventPublisher.publishEvent(this.customerRepository.save(customer));
//
//                return new ResponseEntity<String>("Customer with ID " + id + " has been enabled.", HttpStatus.OK);
//            }
//
//            return new ResponseEntity<String>("Customer with ID " + id + " is already enabled.", HttpStatus.OK);
//        }
//        catch (Exception e) {
//            return new ResponseEntity<String>("An error has occurred: " + e.toString(), HttpStatus.BAD_REQUEST);
//        }

        Customer customer = this.customerRepository.findById(id).get();

        //first check if customer is already enabled
        if(!customer.isActivated()) {
            customer.setActivated(true);

            CustomerUpdatedEventPublisher eventPublisher = new CustomerUpdatedEventPublisher();
            eventPublisher.publishEvent(this.customerRepository.save(customer));
        }

    }

    //so we can access all the nifty crud methods spring magically generates
    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }
}
