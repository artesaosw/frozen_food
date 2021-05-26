package com.capgemini.engineering.ddd.frozen_food.menu.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.IngredientDTO;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Ingredient;

public class IngredientConverter {

    public static IngredientDTO ingredient2IngredientDTO(Ingredient ingredient) {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(ingredient.getId());
        ingredientDTO.setName(ingredient.getDescription());
        ingredientDTO.setUnit(ingredient.getUnit());
        return ingredientDTO;
    }
}
