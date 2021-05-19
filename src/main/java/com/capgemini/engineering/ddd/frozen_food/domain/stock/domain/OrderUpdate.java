package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.OrderID;

public class OrderUpdate extends DomainEvent {

    private OrderID orderID;

    public OrderUpdate(OrderID orderID) {
        super();
        this.orderID = orderID;
    }
}
