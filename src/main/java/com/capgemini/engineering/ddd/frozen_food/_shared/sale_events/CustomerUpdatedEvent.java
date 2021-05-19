package com.capgemini.engineering.ddd.frozen_food._shared.sale_events;

import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Customer;
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
