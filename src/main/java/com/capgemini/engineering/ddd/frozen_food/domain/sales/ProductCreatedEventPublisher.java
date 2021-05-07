package com.capgemini.engineering.ddd.frozen_food.domain.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class ProductCreatedEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final Product product) {
        ProductCreatedEvent productCreatedEvent = new
                ProductCreatedEvent(this, product);

        applicationEventPublisher.publishEvent(productCreatedEvent);
    }
}
