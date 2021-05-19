package com.capgemini.engineering.ddd.frozen_food.menu.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Ingredient;

import javax.validation.constraints.NotBlank;

public interface Ingredients extends Repository<Ingredient, IngredientID> {

    boolean existsWithDescription(@NotBlank String description);

}
