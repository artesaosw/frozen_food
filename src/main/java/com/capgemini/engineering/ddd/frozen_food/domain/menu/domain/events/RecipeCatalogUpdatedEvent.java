package com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.events;

import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.DTO.RecipeDTO;
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
