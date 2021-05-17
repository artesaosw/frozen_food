package com.capgemini.engineering.ddd.frozen_food.domain.producao;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;

public class RecipeProductionRegistered extends DomainEvent {

    private BatchID batchID;

    public RecipeProductionRegistered(BatchID batchID){
        super();
        this.batchID = batchID;
    }
}
