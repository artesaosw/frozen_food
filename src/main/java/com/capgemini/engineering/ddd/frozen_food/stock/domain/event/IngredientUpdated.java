package com.capgemini.engineering.ddd.frozen_food.stock.domain.event;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.IngredientID;

public class IngredientUpdated extends DomainEvent {

    private IngredientID ingredientID;

    public IngredientUpdated(IngredientID ingredientID) {
        super();
        this.ingredientID = ingredientID;
    }
}
