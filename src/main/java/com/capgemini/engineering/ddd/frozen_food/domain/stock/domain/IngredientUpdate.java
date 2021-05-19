package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.IngredientID;

public class IngredientUpdate extends DomainEvent {

    private IngredientID ingredientID;

    public IngredientUpdate(IngredientID ingredientID) {
        super();
        this.ingredientID = ingredientID;
    }
}
