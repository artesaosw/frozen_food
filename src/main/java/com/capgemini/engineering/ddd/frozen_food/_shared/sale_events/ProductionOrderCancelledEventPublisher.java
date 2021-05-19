package com.capgemini.engineering.ddd.frozen_food._shared.sale_events;

import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.ProductionOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class ProductionOrderCancelledEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final ProductionOrder productionOrder) {
        ProductionOrderCancelledEvent productionOrderCancelledEvent = new
                ProductionOrderCancelledEvent(this, productionOrder);

        applicationEventPublisher.publishEvent(productionOrderCancelledEvent);
    }
}
