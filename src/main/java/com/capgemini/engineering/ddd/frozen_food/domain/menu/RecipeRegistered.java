package com.capgemini.engineering.ddd.frozen_food.domain.menu;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RecipeID;
import lombok.Getter;

@Getter
public class RecipeRegistered extends DomainEvent {

    private RecipeID recipeID;

    public RecipeRegistered(RecipeID recipeID) {
        super();
        this.recipeID = recipeID;
    }

}
