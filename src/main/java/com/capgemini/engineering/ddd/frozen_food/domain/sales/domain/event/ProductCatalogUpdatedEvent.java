package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.event;

import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.ProductCatalog;
import org.springframework.context.ApplicationEvent;

public class ProductCatalogUpdatedEvent extends ApplicationEvent {

    private ProductCatalog productCatalog;

    public ProductCatalogUpdatedEvent(Object source, ProductCatalog productCatalog) {
        super(source);
        this.productCatalog = productCatalog;
    }

    public ProductCatalog getProductCatalog() {
        return this.productCatalog;
    }
}
