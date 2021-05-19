package com.capgemini.engineering.ddd.frozen_food.menu.domain;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.DTO.RecipeDTO;
import lombok.Getter;

@Getter
public class RecipeRegistered extends DomainEvent {

    private RecipeDTO recipeDTO;

    public RecipeRegistered(RecipeDTO recipeDTO) {
        super();
        this.recipeDTO = recipeDTO;

    }
}
