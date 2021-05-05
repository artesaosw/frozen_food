package com.capgemini.engineering.ddd.frozen_food.domain.stock;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderSupplierID;

public class OrderSupplierRegistered extends DomainEvent {

    private OrderSupplierID orderSupplierID;

    public OrderSupplierRegistered(OrderSupplierID orderSupplierID) {
        super();
        this.orderSupplierID = orderSupplierID;
    }
}
