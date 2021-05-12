package com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.converter;

import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.DTO.RecipeDTO;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.entity.Recipe;

public class RecipeConverter {

    public static RecipeDTO recipe2RecipeDTO(Recipe recipe) {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(recipe.getId());
        recipeDTO.setName(recipe.getName());
        //recipeDTO.setItems();
        return recipeDTO;
    }
}

