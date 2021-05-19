package com.capgemini.engineering.ddd.frozen_food.sales.domain.service;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food._shared.sale_events.CustomerDeletedEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.sale_events.CustomerRegisteredEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.sale_events.CustomerUpdatedEvent;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.exception.BillingInfoAlreadyExistsException;
import com.capgemini.engineering.ddd.frozen_food.sales.infra.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Service
public class CustomerServiceImpl implements DomainServices, CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public Customer createNewCustomer (Customer customer) {

        // can't have two customers with the same billing info
        // IS THIS CORRECT ??????????
        if (customerRepository.existsCustomerByNif(customer.getNif())) {
            throw new BillingInfoAlreadyExistsException("Customer creation failed. A customer with the same billing information already exists.");
        }

        //persist new customer into the database and issue an event
        customer = this.customerRepository.save(customer);

        eventPublisher.publishEvent(new CustomerRegisteredEvent(this, customer));

        return customer;
    }

    public Customer updateCustomer(Customer customer) {
        //update the customer and issue an event
        customer = this.customerRepository.save(customer);

        eventPublisher.publishEvent(new CustomerUpdatedEvent(this, customer));

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
        eventPublisher.publishEvent(new CustomerDeletedEvent(this, id));
    }

    /*
    Disables customer with the ID passed and issues a CustomerUpdatedEvent.
     */
    public void disableCustomer(@NotBlank String id) {

        Customer customer = this.customerRepository.findById(id).get();

        //first check if customer is already disabled
        if(customer.isActivated()) {
            customer.setActivated(false);

            CustomerUpdatedEvent updateEvent =
                    new CustomerUpdatedEvent(this, this.customerRepository.save(customer));
            eventPublisher.publishEvent(updateEvent);
        }
    }

    /*
    Enables customer with the ID passed and issues a CustomerUpdatedEvent.
     */
    public void enableCustomer(@NotBlank String id) {

        Customer customer = this.customerRepository.findById(id).get();

        //first check if customer is already enabled
        if(!customer.isActivated()) {
            customer.setActivated(true);

            CustomerUpdatedEvent updateEvent =
                    new CustomerUpdatedEvent(this, this.customerRepository.save(customer));
            eventPublisher.publishEvent(updateEvent);
        }

    }

    //so we can access all the nifty crud methods spring magically generates
    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }
}
