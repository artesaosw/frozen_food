package com.capgemini.engineering.ddd.frozen_food.menu.domain;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.id.RecipeID;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Recipe;
import lombok.Getter;

@Getter
public class RecipeUpdated extends DomainEvent {

    private RecipeID recipeID;
    private Recipe recipe;

    public RecipeUpdated(RecipeID recipeID, Recipe recipe) {
        super();
        this.recipeID = recipeID;
        this.recipe = recipe;
    }
}
