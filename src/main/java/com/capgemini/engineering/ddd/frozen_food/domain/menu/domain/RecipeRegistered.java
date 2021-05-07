package com.capgemini.engineering.ddd.frozen_food.domain.menu.domain;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RecipeID;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.DTO.Recipe_SalesDTO;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.entity.Recipe;
import lombok.Getter;

@Getter
public class RecipeRegistered extends DomainEvent {

    private Recipe_SalesDTO recipe_SalesDTO;

    public RecipeRegistered(Recipe_SalesDTO recipe_SalesDTO) {
        super();
        this.recipe_SalesDTO = recipe_SalesDTO;
    }

}
