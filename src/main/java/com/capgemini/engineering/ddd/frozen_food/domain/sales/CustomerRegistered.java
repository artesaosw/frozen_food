package com.capgemini.engineering.ddd.frozen_food.domain.sales;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.CustomerID;

public class CustomerRegistered extends DomainEvent {

    private CustomerID customerID;

    public CustomerRegistered(CustomerID customerID) {
        super();
        this.customerID = customerID;
    }
}
