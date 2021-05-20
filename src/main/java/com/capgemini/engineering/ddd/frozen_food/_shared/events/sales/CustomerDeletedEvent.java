package com.capgemini.engineering.ddd.frozen_food._shared.events.sales;

import com.capgemini.engineering.ddd.frozen_food._shared.id.CustomerID;
import org.springframework.context.ApplicationEvent;

public class CustomerDeletedEvent extends ApplicationEvent {

    private CustomerID customerID;

    public CustomerDeletedEvent(Object source, CustomerID customerID) {
        super(source);
        this.customerID = customerID;
    }

    public CustomerID getId() {
        return this.customerID;
    }
}
