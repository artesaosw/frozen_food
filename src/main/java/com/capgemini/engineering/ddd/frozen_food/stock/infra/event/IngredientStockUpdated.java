package com.capgemini.engineering.ddd.frozen_food.stock.infra.event;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;

public class IngredientStockUpdated extends DomainEvent {

    private IngredientID ingredientID;

    public IngredientStockUpdated(IngredientID ingredientID) {
        super();
        this.ingredientID = ingredientID;
    }
}
