package com.capgemini.engineering.ddd.frozen_food._shared.events.sales;

import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Order;
import org.springframework.context.ApplicationEvent;

public class OrderRegisteredEvent extends ApplicationEvent {

    private Order order;

    public OrderRegisteredEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}