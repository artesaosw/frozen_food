package com.capgemini.engineering.ddd.frozen_food._shared.events.sales;

import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.ProductionOrder;
import org.springframework.context.ApplicationEvent;

public class ProductionOrderIssuedEvent extends ApplicationEvent {

    private ProductionOrder productionOrder;

    public ProductionOrderIssuedEvent(Object source, ProductionOrder productionOrder) {
        super(source);
        this.productionOrder = productionOrder;
    }

    public ProductionOrder getProductionOrder() {
        return this.productionOrder;
    }
}
