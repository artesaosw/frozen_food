package com.capgemini.engineering.ddd.frozen_food._shared.events.sales;

import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Customer;
import org.springframework.context.ApplicationEvent;

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
