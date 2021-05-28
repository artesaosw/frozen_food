package com.capgemini.engineering.ddd.frozen_food._shared.event.sales_delivery;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery.DeliveryOrderDTO;
import org.springframework.context.ApplicationEvent;

public class OrderRegisteredEvent extends ApplicationEvent {

    private DeliveryOrderDTO deliveryOrderDTO;

    public OrderRegisteredEvent(Object source, DeliveryOrderDTO deliveryOrderDTO) {
        super(source);
        this.deliveryOrderDTO = deliveryOrderDTO;
    }

    public DeliveryOrderDTO getOrder() {
        return deliveryOrderDTO;
    }
}
