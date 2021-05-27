package com.capgemini.engineering.ddd.frozen_food._shared.events.sales;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductID;
import org.springframework.context.ApplicationEvent;

public class ProductRemovedEvent extends ApplicationEvent {

    private ProductID productID;

    public ProductRemovedEvent(Object source, ProductID productID) {
        super(source);
        this.productID = productID;
    }

    public ProductID getProductID() {
        return this.productID;
    }
}
