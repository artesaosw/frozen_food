package com.capgemini.engineering.ddd.frozen_food.menu.domain.service;

import com.capgemini.engineering.ddd.frozen_food.Events;
import com.capgemini.engineering.ddd.frozen_food.menu.Menu;
import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.IngredientRegistered;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.repository.Ingredients;

import javax.validation.constraints.NotBlank;

public class MantainIngredients implements DomainServices {

    private Ingredients ingredients() { return Menu.ingredients(); }

    public void registerNew(@NotBlank String description){

        //Validation
        if(ingredients().existsWithDescription(description)){
            throw new IllegalArgumentException("There is already exists another ingredient with the same description.");
        }

        //Instantiate aggregate
        Ingredient ingredient = new Ingredient(description);

        //persists
        ingredients().registerNew(ingredient);

        Events.report(new IngredientRegistered (ingredient.getId()));
    }

}
