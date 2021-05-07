package com.capgemini.engineering.ddd.frozen_food.domain.sales;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderID;
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
