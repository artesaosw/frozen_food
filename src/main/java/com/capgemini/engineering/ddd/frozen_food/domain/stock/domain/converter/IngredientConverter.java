package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.converter;

import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.dto.IngredientDTO;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientConverter {

    public static IngredientDTO ingredient2IngredientDTO(Ingredient ingredient) throws NullPointerException {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(ingredient.getId());
        ingredientDTO.setName(ingredientDTO.getName());
        return ingredientDTO;
    }

    public static Ingredient ingredientDTO2Ingredient(IngredientDTO ingredientDTO) throws NullPointerException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientDTO.getId());
        ingredient.setName(ingredientDTO.getName());
        return ingredient;
    }

    public static List<IngredientDTO> ingredientList2IngredientListDTO(List<Ingredient> ingredientList) throws NullPointerException {
        List<IngredientDTO> ingredientDTOList = new ArrayList<>();
        for (Ingredient ingredient : ingredientList) {
            IngredientDTO ingredientDTO = ingredient2IngredientDTO(ingredient);
            ingredientDTOList.add(ingredientDTO);
        }
        return ingredientDTOList;
    }

    public static List<Ingredient> ingredientListDTO2IngredientList(List<IngredientDTO> ingredientDTOList) throws NullPointerException {
        List<Ingredient> ingredientList = new ArrayList<>();
        for (IngredientDTO ingredientDTO : ingredientDTOList) {
            Ingredient ingredient = ingredientDTO2Ingredient(ingredientDTO);
            ingredientList.add(ingredient);
        }
        return ingredientList;
    }
}
