package com.capgemini.engineering.ddd.frozen_food._shared.events.sales;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery.OrderDTO;
import org.springframework.context.ApplicationEvent;

public class DeliveryRequestedEvent extends ApplicationEvent {

    private OrderDTO orderDTO;

    public DeliveryRequestedEvent(Object source, OrderDTO orderDTO) {
        super(source);
        this.orderDTO = orderDTO;
    }

    public OrderDTO getOrderDTO() {
        return this.orderDTO;
    }
}
