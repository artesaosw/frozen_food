package com.capgemini.engineering.ddd.frozen_food._shared.event.sales_production;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import org.springframework.context.ApplicationEvent;

public class ProductionOrderCancelledEvent extends ApplicationEvent {

    private ProductionOrderID productionOrderID;

    public ProductionOrderCancelledEvent(Object source, ProductionOrderID productionOrderID) {
        super(source);
        this.productionOrderID = productionOrderID;
    }

    public ProductionOrderID getProductionOrderID() {
        return this.productionOrderID;
    }
}
