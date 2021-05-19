package com.capgemini.engineering.ddd.frozen_food.domain.producao.event;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;

public class RecipeProductionCanceled extends DomainEvent {

    private BatchID batchID;

    public RecipeProductionCanceled(BatchID batchID){
        super();
        this.batchID = batchID;
    }
}
