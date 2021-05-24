package com.capgemini.engineering.ddd.frozen_food.stock.infra.event;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;

public class ProductionOrderRegistered extends DomainEvent {

    private ProductionOrderID productionOrderID;

    public ProductionOrderRegistered(ProductionOrderID productionOrderID) {
        super();
        this.productionOrderID = productionOrderID;
    }
}
