package com.capgemini.engineering.ddd.frozen_food.domain.sales;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderID;

public class OrderRegistered extends DomainEvent {

    private OrderID orderID;

    public OrderRegistered(OrderID orderID) {
        this.orderID = orderID;
    }
}
