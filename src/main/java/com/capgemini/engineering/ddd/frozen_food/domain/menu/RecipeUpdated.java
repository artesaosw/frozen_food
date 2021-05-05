package com.capgemini.engineering.ddd.frozen_food.domain.menu;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ids.RecipeID;
import lombok.Getter;

@Getter
public class RecipeUpdated extends DomainEvent {

    private RecipeID recipeID;

    public RecipeUpdated(RecipeID recipeID) {
        super();
        this.recipeID = recipeID;
    }
}
