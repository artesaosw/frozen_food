package com.capgemini.engineering.ddd.frozen_food.domain.menu.domain;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RecipeID;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.DTO.RecipeDTO;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.entity.Recipe;
import lombok.Getter;

@Getter
public class RecipeRegistered extends DomainEvent {

    private RecipeDTO recipeDTO;

    public RecipeRegistered(RecipeDTO recipeDTO) {
        super();
        this.recipeDTO = recipeDTO;

    }
}
