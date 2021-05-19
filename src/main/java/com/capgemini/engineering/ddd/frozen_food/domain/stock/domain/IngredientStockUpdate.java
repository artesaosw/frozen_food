package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.IngredientID;

public class IngredientStockUpdate extends DomainEvent {

    private IngredientID ingredientID;

    public IngredientStockUpdate(IngredientID ingredientID) {
        super();
        this.ingredientID = ingredientID;
    }
}
