package com.capgemini.engineering.ddd.frozen_food.stock.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.stock.dto.IngredientDTO;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientConverter {

    public static IngredientDTO ingredient2IngredientDTO(Ingredient ingredient) throws NullPointerException {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(ingredient.getIngredientID());
        ingredientDTO.setName(ingredient.getName());
        ingredientDTO.setUnit(ingredient.getUnit());
        return ingredientDTO;
    }

    public static Ingredient ingredientDTO2Ingredient(IngredientDTO ingredientDTO) throws NullPointerException {
        Ingredient ingredient = new Ingredient(ingredientDTO.getName(), ingredientDTO.getUnit());
        ingredient.setIngredientID(ingredientDTO.getId());
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
