package com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RecipeID;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.entity.Recipe;

import javax.validation.constraints.NotBlank;

public interface Recipes extends Repository<Recipe, RecipeID> {

    boolean existsWithName(@NotBlank String name);

}
