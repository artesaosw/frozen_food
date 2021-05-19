package com.capgemini.engineering.ddd.frozen_food.sales.domain.event;

import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Order;
import org.springframework.context.ApplicationEvent;

public class PaymentReturnedEvent extends ApplicationEvent {

    private Order order;

    public PaymentReturnedEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }

    public Order getOrder() {
        return this.order;
    }
}
