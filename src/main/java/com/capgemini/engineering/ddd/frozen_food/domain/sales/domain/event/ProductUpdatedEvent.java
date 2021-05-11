package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.event;

import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Product;
import org.springframework.context.ApplicationEvent;

public class ProductUpdatedEvent extends ApplicationEvent {

    private Product product;

    public ProductUpdatedEvent(Object source, Product product) {
        super(source);
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }
}