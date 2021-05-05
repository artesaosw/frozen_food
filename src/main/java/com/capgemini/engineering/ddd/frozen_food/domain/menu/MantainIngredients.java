package com.capgemini.engineering.ddd.frozen_food.domain.menu;

import com.capgemini.engineering.ddd.frozen_food.domain.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;

import javax.validation.constraints.NotBlank;

public class MantainIngredients implements DomainServices {

    private Ingredients ingredients() { return Domain.ingredients(); }

    public void registerNew(@NotBlank String description){

        //Validation
        if(ingredients().existsWithDescription(description)){
            throw new IllegalArgumentException("There is already exists another ingredient with the same description.");
        }

        //Instantiate aggregate
        Ingredient ingredient = new Ingredient(description);

        //persists
        ingredients().registerNew(ingredient);


    }

}
