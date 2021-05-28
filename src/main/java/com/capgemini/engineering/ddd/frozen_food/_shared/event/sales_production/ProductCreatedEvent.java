package com.capgemini.engineering.ddd.frozen_food._shared.event.sales_production;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductID;
import org.springframework.context.ApplicationEvent;

public class ProductCreatedEvent extends ApplicationEvent {

    private ProductID productID;

    public ProductCreatedEvent(Object source, ProductID productID) {
        super(source);
        this.productID = productID;
    }

    public ProductID getProductID() {
        return this.productID;
    }
}
