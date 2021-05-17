package com.capgemini.engineering.ddd.frozen_food.domain.producao;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.ProducedRecipe;

public class RecipeProductionClosed extends DomainEvent {

    private BatchID batchID;

    public RecipeProductionClosed(BatchID batchID){
        super();
        this.batchID = batchID;
    }
}
