package com.capgemini.engineering.ddd.frozen_food.stock.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_production_stock.IngredientDTO;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;

public class IngredientConverter {

    public static IngredientDTO ingredient2IngredientDTO(Ingredient ingredient) throws NullPointerException {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(ingredient.getId());
        ingredientDTO.setName(ingredient.getName());
        ingredientDTO.setUnit(ingredient.getUnit());
        return ingredientDTO;
    }

    public static Ingredient ingredientDTO2Ingredient(IngredientDTO ingredientDTO) throws NullPointerException {
        Ingredient ingredient = new Ingredient(ingredientDTO.getId(), ingredientDTO.getName(), ingredientDTO.getUnit());
        return ingredient;
    }
}
