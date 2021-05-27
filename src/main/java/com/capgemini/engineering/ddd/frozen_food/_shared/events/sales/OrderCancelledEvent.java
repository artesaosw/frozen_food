package com.capgemini.engineering.ddd.frozen_food._shared.events.sales;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery.DeliveryOrderDTO;
import org.springframework.context.ApplicationEvent;

public class OrderCancelledEvent extends ApplicationEvent {

    private DeliveryOrderDTO deliveryOrderDTO;

    public OrderCancelledEvent(Object source, DeliveryOrderDTO deliveryOrderDTO) {
        super(source);
        this.deliveryOrderDTO = deliveryOrderDTO;
    }

    public DeliveryOrderDTO getDeliveryOrderDTO() {
        return deliveryOrderDTO;
    }
}
