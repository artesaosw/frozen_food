package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.event;

import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomerUpdatedEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final Customer customer) {
        CustomerUpdatedEvent customerUpdatedEvent = new
                CustomerUpdatedEvent(this, customer);

        applicationEventPublisher.publishEvent(customerUpdatedEvent);
    }
}
