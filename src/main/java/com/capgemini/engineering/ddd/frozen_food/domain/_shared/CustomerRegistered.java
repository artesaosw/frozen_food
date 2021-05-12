package com.capgemini.engineering.ddd.frozen_food.domain._shared;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;

import java.util.UUID;

class CustomerData{
    private UUID id;
    private String name;

}

public class CustomerRegistered extends DomainEvent {
    public CustomerRegistered(String eventData) {
        super(eventData);
    }
}
