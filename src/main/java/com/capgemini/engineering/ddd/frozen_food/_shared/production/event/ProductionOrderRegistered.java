package com.capgemini.engineering.ddd.frozen_food._shared.production.event;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.id.BatchID;

public class ProductionOrderRegistered extends DomainEvent {

    private BatchID batchID;

    public ProductionOrderRegistered(BatchID batchID){
        super();
        this.batchID = batchID;
    }
}
