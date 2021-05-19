package com.capgemini.engineering.ddd.frozen_food.menu.domain;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.IngredientID;

public class IngredientRegistered extends DomainEvent {

    private IngredientID ingredientID;

    public IngredientRegistered(IngredientID ingredientID){
        super();
        this.ingredientID = ingredientID;
    }
}
