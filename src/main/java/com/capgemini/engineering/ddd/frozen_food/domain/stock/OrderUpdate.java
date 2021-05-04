package com.capgemini.engineering.ddd.frozen_food.domain.stock;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderID;

public class OrderUpdate extends DomainEvent {

    private OrderID orderID;

    public OrderUpdate(OrderID orderID) {
        super();
        this.orderID = orderID;
    }
}
