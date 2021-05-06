package com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.converter;

import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.DTO.Recipe_SalesDTO;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.entity.Recipe;

public class Recipe_SalesConverter {

    public static Recipe_SalesDTO recipe2Recipe_SalesDTO(Recipe recipe) {
        Recipe_SalesDTO recipe_SalesDTO = new Recipe_SalesDTO();
        recipe_SalesDTO.setId(recipe.getId());
        recipe_SalesDTO.setName(recipe.getName());
        return recipe_SalesDTO;
    }
}

