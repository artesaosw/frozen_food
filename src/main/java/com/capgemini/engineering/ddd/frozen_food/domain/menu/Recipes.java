package com.capgemini.engineering.ddd.frozen_food.domain.menu;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ids.RecipeID;

import javax.validation.constraints.NotBlank;

public interface Recipes extends Repository<Recipe, RecipeID> {

    boolean existsWithName(@NotBlank String name);

}
