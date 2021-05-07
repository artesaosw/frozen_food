package com.capgemini.engineering.ddd.frozen_food.domain.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class OrderUpdatedEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final Order order) {
        OrderUpdatedEvent orderUpdatedEvent = new
                OrderUpdatedEvent(this, order);

        applicationEventPublisher.publishEvent(orderUpdatedEvent);
    }

}
