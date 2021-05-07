package com.capgemini.engineering.ddd.frozen_food.domain.sales;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.CustomerID;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

public class CustomerRegisteredEvent extends ApplicationEvent {

    private Customer customer;

    public CustomerRegisteredEvent(Object source, Customer customer) {
        super(source);
        this.customer = customer;
    }

    public Customer getCustomer() {
        return this.customer;
    }
}
