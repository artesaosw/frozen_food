package com.capgemini.engineering.ddd.frozen_food.menu.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_production.RecipeDTO;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Recipe;

public class RecipeConverter {

    public static RecipeDTO recipe2RecipeDTO(Recipe recipe) {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(recipe.getId());
        recipeDTO.setName(recipe.getName());
        recipeDTO.setItems(recipe.getItems());
        return recipeDTO;
    }
}

