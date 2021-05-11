package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.event;

import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.ProductionOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class ProductionOrderIssuedEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final ProductionOrder productionOrder) {
        ProductionOrderIssuedEvent productionOrderIssuedEvent = new
                ProductionOrderIssuedEvent(this, productionOrder);

        applicationEventPublisher.publishEvent(productionOrderIssuedEvent);
    }
}
