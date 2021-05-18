package com.capgemini.engineering.ddd.frozen_food.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface Ingredients extends Repository<Ingredient, IngredientID> {

    boolean existsWithName(@NotBlank String name);

    boolean existsMinimumStock(@NotBlank String name);

    boolean delete(@NotNull IngredientID id);
}
