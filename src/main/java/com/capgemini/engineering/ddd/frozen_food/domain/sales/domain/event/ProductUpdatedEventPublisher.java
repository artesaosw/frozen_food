package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.event;

import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class ProductUpdatedEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final Product product) {
        ProductUpdatedEvent productUpdatedEvent = new
                ProductUpdatedEvent(this, product);

        applicationEventPublisher.publishEvent(productUpdatedEvent);
    }
}
