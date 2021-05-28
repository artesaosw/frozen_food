package com.capgemini.engineering.ddd.frozen_food._shared.event.sales_delivery;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery.DeliveryOrderDTO;
import org.springframework.context.ApplicationEvent;

public class DeliveryRequestedEvent extends ApplicationEvent {

    private DeliveryOrderDTO deliveryOrderDTO;

    public DeliveryRequestedEvent(Object source, DeliveryOrderDTO deliveryOrderDTO) {
        super(source);
        this.deliveryOrderDTO = deliveryOrderDTO;
    }

    public DeliveryOrderDTO getDeliveryOrderDTO() {
        return this.deliveryOrderDTO;
    }
}
