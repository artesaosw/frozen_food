package com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.converter;

import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.DTO.ExperimentRecipeDTO;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.valueObject.ExperimentRecipe;

public class ExperimentRecipeConverter {

    public static ExperimentRecipeDTO experimentRecipe2ExperimentRecipeDTO(ExperimentRecipe experimentRecipe) {
        ExperimentRecipeDTO experimentRecipeDTO = new ExperimentRecipeDTO();
        experimentRecipeDTO.setItems(experimentRecipe.getItems());
        return experimentRecipeDTO;
    }
}
