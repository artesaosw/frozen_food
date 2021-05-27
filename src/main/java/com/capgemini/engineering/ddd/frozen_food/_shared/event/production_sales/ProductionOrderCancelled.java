package com.capgemini.engineering.ddd.frozen_food._shared.event.production_sales;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.id.BatchID;

public class ProductionOrderCancelled extends DomainEvent {

    private BatchID batchID;

    public ProductionOrderCancelled(BatchID batchID){
        super();
        this.batchID = batchID;
    }
}
