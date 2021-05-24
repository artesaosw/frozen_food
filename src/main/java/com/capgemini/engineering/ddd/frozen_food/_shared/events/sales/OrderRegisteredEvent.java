package com.capgemini.engineering.ddd.frozen_food._shared.events.sales;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery.OrderDTO;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Order;
import org.springframework.context.ApplicationEvent;

public class OrderRegisteredEvent extends ApplicationEvent {

    private OrderDTO orderDTO;

    public OrderRegisteredEvent(Object source, OrderDTO orderDTO) {
        super(source);
        this.orderDTO = orderDTO;
    }

    public OrderDTO getOrder() {
        return orderDTO;
    }
}
