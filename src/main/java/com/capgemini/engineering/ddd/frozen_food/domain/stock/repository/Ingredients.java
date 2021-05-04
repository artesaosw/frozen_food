package com.capgemini.engineering.ddd.frozen_food.domain.stock.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.entity.Ingredient;

import javax.validation.constraints.NotBlank;

public interface Ingredients extends Repository<Ingredient, IngredientID> {

    boolean existsWithName(@NotBlank String name);

    boolean existsMinimumStock(@NotBlank String name);
}
