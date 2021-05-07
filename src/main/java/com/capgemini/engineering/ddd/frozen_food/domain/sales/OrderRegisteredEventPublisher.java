package com.capgemini.engineering.ddd.frozen_food.domain.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class OrderRegisteredEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final Order order) {
        OrderRegisteredEvent orderRegisteredEvent = new
                OrderRegisteredEvent(this, order);

        applicationEventPublisher.publishEvent(orderRegisteredEvent);
    }

}
