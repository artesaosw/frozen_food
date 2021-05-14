package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductionOrderID;

public class ProductionOrderUpdated extends DomainEvent {

    private ProductionOrderID productionOrderID;

    public ProductionOrderUpdated(ProductionOrderID productionOrderID) {
        super();
        this.productionOrderID = productionOrderID;
    }
}
