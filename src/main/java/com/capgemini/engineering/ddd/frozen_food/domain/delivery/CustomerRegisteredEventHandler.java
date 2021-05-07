package com.capgemini.engineering.ddd.frozen_food.domain.delivery;

import com.capgemini.engineering.ddd.frozen_food.domain.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.CustomerRegisteredEvent;
import org.springframework.context.ApplicationListener;

public class CustomerRegisteredEventHandler implements ApplicationListener<CustomerRegisteredEvent> {

    @Override
    public void onApplicationEvent(CustomerRegisteredEvent customerRegisteredEvent) {
        System.out.println("Customer has been registered: " + customerRegisteredEvent.getMessage());
    }
}
