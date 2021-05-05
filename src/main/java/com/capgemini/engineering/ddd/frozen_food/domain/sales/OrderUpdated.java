package com.capgemini.engineering.ddd.frozen_food.domain.sales;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderID;

public class OrderUpdated extends DomainEvent {

    private OrderID orderID;

    public OrderUpdated(OrderID orderID) {
        this.orderID = orderID;
    }
}
