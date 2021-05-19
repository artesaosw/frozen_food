package com.capgemini.engineering.ddd.frozen_food._shared.sale_events;

import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class PaymentReturnedEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final Order order) {
        PaymentReturnedEvent paymentReturnedEvent = new
                PaymentReturnedEvent(this, order);

        applicationEventPublisher.publishEvent(paymentReturnedEvent);
    }
}
