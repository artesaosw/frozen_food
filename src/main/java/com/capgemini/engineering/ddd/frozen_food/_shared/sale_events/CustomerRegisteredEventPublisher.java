package com.capgemini.engineering.ddd.frozen_food._shared.sale_events;

import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomerRegisteredEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final Customer customer) {
        CustomerRegisteredEvent customerRegisteredEvent = new
                CustomerRegisteredEvent(this, customer);

        applicationEventPublisher.publishEvent(customerRegisteredEvent);
    }
}
