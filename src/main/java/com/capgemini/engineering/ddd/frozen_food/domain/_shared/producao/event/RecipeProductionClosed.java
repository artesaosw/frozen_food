package com.capgemini.engineering.ddd.frozen_food.domain._shared.producao.event;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;

public class RecipeProductionClosed extends DomainEvent {

    private BatchID batchID;

    public RecipeProductionClosed(BatchID batchID){
        super();
        this.batchID = batchID;
    }
}
