package com.capgemini.engineering.ddd.frozen_food.menu.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food._shared.RecipeID;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Recipe;

import javax.validation.constraints.NotBlank;

public interface Recipes extends Repository<Recipe, RecipeID> {

    boolean existsWithName(@NotBlank String name);

}
