package com.capgemini.engineering.ddd.frozen_food.domain.menu;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RecipeID;
import lombok.Getter;

@Getter
public class RecipeRegistered extends DomainEvent {

    private RecipeID recipeID;
    private Recipe recipe;

    public RecipeRegistered(RecipeID recipeID, Recipe recipe) {
        super();
        this.recipeID = recipeID;
        this.recipe = recipe;
    }

}
