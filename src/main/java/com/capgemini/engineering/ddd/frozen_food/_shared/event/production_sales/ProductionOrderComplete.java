package com.capgemini.engineering.ddd.frozen_food._shared.event.production_sales;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.id.BatchID;

public class ProductionOrderComplete extends DomainEvent {

    private BatchID batchID;

    public ProductionOrderComplete(BatchID batchID){
        super();
        this.batchID = batchID;
    }
}