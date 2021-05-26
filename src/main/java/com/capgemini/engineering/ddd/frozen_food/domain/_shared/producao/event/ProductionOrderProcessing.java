package com.capgemini.engineering.ddd.frozen_food.domain._shared.producao.event;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;

public class ProductionOrderProcessing extends DomainEvent {

    private BatchID batchID;

    public ProductionOrderProcessing(BatchID batchID){
        super();
        this.batchID = batchID;
    }
}
