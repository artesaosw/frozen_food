package com.capgemini.engineering.ddd.frozen_food.domain.menu;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.IngredientID;

import javax.validation.constraints.NotBlank;

public interface Ingredients extends Repository<Ingredient, IngredientID> {

    boolean existsWithDescription(@NotBlank String description);

}
