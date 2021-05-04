package com.capgemini.engineering.ddd.frozen_food.domain.stock;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.IngredientID;
import lombok.Getter;

@Getter
public class IngredientRegistered extends DomainEvent {

    private IngredientID ingredientID;

    public IngredientRegistered(IngredientID ingredientID) {
        super();
        this.ingredientID = ingredientID;
    }
}
