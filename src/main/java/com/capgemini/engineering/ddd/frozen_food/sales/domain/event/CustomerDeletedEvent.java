package com.capgemini.engineering.ddd.frozen_food.sales.domain.event;

import org.springframework.context.ApplicationEvent;

public class CustomerDeletedEvent extends ApplicationEvent {

    private String id;

    public CustomerDeletedEvent(Object source, String id) {
        super(source);
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
