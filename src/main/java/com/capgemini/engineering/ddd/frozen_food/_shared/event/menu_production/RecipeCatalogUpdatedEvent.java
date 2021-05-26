package com.capgemini.engineering.ddd.frozen_food._shared.event.menu_production;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_production.RecipeDTO;
import org.springframework.context.ApplicationEvent;

public class RecipeCatalogUpdatedEvent extends ApplicationEvent {

    RecipeDTO recipeDTO;

    public RecipeCatalogUpdatedEvent(Object source, RecipeDTO recipeDTO) {
        super(source);
        this.recipeDTO = recipeDTO;
    }

    public RecipeDTO getRecipeDTO() {
        return this.recipeDTO;
    }
}
