package com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.events;

import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.DTO.RecipeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class RecipeCatalogUpdatedPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(final RecipeDTO recipeDTO) {
        RecipeCatalogUpdatedEvent recipeCatalogUpdatedEvent = new
                RecipeCatalogUpdatedEvent(this, recipeDTO);

        applicationEventPublisher.publishEvent(recipeCatalogUpdatedEvent);

    }
}
