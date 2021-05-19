package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.OrderID;

public class OrderRegistered extends DomainEvent {

    private OrderID orderID;

    public OrderRegistered(OrderID orderID) {
        super();
        this.orderID = orderID;
    }
}
