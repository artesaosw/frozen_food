package com.capgemini.engineering.ddd.frozen_food.domain.sales;

import org.springframework.context.ApplicationEvent;

public class CustomerUpdatedEvent extends ApplicationEvent {

    private Customer customer;

    public CustomerUpdatedEvent(Object source, Customer customer) {
        super(source);
        this.customer = customer;
    }

    public Customer getCustomer() {
        return this.customer;
    }
}
