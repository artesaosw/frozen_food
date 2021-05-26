package com.capgemini.engineering.ddd.frozen_food.domain._shared.producao.event;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;

public class ProductionOrderCancelled extends DomainEvent {

    private BatchID batchID;

    public ProductionOrderCancelled(BatchID batchID){
        super();
        this.batchID = batchID;
    }
}
