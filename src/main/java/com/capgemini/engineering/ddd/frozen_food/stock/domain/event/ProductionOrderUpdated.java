package com.capgemini.engineering.ddd.frozen_food.stock.domain.event;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.ProductionOrderID;

public class ProductionOrderUpdated extends DomainEvent {

    private ProductionOrderID productionOrderID;

    public ProductionOrderUpdated(ProductionOrderID productionOrderID) {
        super();
        this.productionOrderID = productionOrderID;
    }
}
