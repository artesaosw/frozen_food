package com.capgemini.engineering.ddd.frozen_food.sales.domain.service;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery.CustomerDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.id.CustomerID;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.converter.CustomerToCustomerDTOConverter;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food._shared.events.sales.CustomerDeletedEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.events.sales.CustomerRegisteredEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.events.sales.CustomerUpdatedEvent;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.exception.InfoAlreadyExistsException;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.exception.DomainIndependentIDMismatchException;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.repository.CustomerRepository;
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

        //can't have two customers with the same NIF
        if (customerRepository.existsCustomerByNif(customer.getNif())
                || customerRepository.existsCustomerByEmail(customer.getEmail()) ) {
            throw new InfoAlreadyExistsException("Customer creation failed. A customer with the same billing or email information already exists.");
        }

        //assign a new CustomerID to the customer object
        customer.setCustomerID(Identificator.newInstance(CustomerID.class));

        //persist new customer into the database and issue an event
        customer = this.customerRepository.save(customer);

        //convert customer to customerDTO and publish new event
        CustomerDTO customerDTO = CustomerToCustomerDTOConverter.convertCustomerToCustomerDTO(customer);
        this.eventPublisher.publishEvent(new CustomerRegisteredEvent(this, customerDTO));

        return customer;
    }

    public Customer updateCustomer(Customer customer) {

        //fetch the customer from the db and compare its CustomerID with the CustomerID
        //of the object passed as argument. They must match.
        Customer dbCustomer = this.customerRepository.findById(customer.getId()).get();
        if(!dbCustomer.getCustomerID().isEqualsTo(customer.getCustomerID())) {
            throw new DomainIndependentIDMismatchException("CustomerID of the received customer entity does not match the CustomerID" +
                    "of the same entity in the database.");
        }

        //update the customer and issue an event
        customer = this.customerRepository.save(customer);

        //convert customer to customerDTO and publish new event
        CustomerDTO customerDTO = CustomerToCustomerDTOConverter.convertCustomerToCustomerDTO(customer);
        this.eventPublisher.publishEvent(new CustomerUpdatedEvent(this, customerDTO));

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
        Customer customer = this.customerRepository.findById(id).get();
        this.customerRepository.deleteById(id);
        this.eventPublisher.publishEvent(new CustomerDeletedEvent(this, customer.getCustomerID()));
    }

    /*
    Disables customer with the ID passed and issues a CustomerUpdatedEvent.
     */
    public void disableCustomer(@NotBlank String id) {

        Customer customer = this.customerRepository.findById(id).get();

        //first check if customer is already disabled
        if(customer.isActivated()) {
            customer.setActivated(false);
            customer = this.customerRepository.save(customer);

            //convert customer to customerDTO and publish new event
            CustomerUpdatedEvent updateEvent =
                    new CustomerUpdatedEvent(this,
                            CustomerToCustomerDTOConverter.convertCustomerToCustomerDTO(customer));
            this.eventPublisher.publishEvent(updateEvent);
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
            customer = this.customerRepository.save(customer);

            //convert customer to customerDTO and publish new event
            CustomerUpdatedEvent updateEvent =
                    new CustomerUpdatedEvent(this,
                            CustomerToCustomerDTOConverter.convertCustomerToCustomerDTO(customer));
            this.eventPublisher.publishEvent(updateEvent);
        }

    }

    //so we can access all the nifty crud methods spring magically generates
    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

}
