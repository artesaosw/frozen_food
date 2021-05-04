package com.capgemini.engineering.ddd.frozen_food.domain.stock;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.IngredientID;

public class IngredientStockUpdate extends DomainEvent {

    private IngredientID ingredientID;

    public IngredientStockUpdate(IngredientID ingredientID) {
        super();
        this.ingredientID = ingredientID;
    }
}
