package com.capgemini.engineering.ddd.frozen_food.domain.sales;

import org.springframework.context.ApplicationEvent;

public class ProductCreatedEvent extends ApplicationEvent {

    private Product product;

    public ProductCreatedEvent(Object source, Product product) {
        super(source);
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }
}
