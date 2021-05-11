package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.event;

import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.ProductCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class ProductCatalogUpdatedEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final ProductCatalog productCatalog) {
        ProductCatalogUpdatedEvent productCatalogUpdatedEvent = new
                ProductCatalogUpdatedEvent(this, productCatalog);

        applicationEventPublisher.publishEvent(productCatalog);
    }
}
