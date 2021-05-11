package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.event;

import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Order;
import org.springframework.context.ApplicationEvent;

public class OrderCanceledEvent extends ApplicationEvent {

    private Order order;

    public OrderCanceledEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}