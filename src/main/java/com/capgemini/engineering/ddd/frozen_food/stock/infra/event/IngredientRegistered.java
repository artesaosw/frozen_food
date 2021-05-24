package com.capgemini.engineering.ddd.frozen_food.stock.infra.event;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import lombok.Getter;

@Getter
public class IngredientRegistered extends DomainEvent {

    private IngredientID ingredientID;

    public IngredientRegistered(IngredientID ingredientID) {
        super();
        this.ingredientID = ingredientID;
    }
}
