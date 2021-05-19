package com.capgemini.engineering.ddd.frozen_food.domain.menu;

import com.capgemini.engineering.ddd.frozen_food.domain.Events;
import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.RecipeID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class MantainRecipes implements DomainServices {

    private Recipes recipes(){
        return Domain.recipes();
    }

    public void registerNew(@NotBlank String name, @NotEmpty Set<Portion> items, @NotBlank String procedure){

        //Validation
        if (recipes().existsWithName(name)){
            throw new IllegalArgumentException("There is already exists another recipe with the same name.");
        }

        //Instantiate aggregate
        Recipe recipe = new Recipe(name, items, procedure);

        //persists
        recipes().registerNew(recipe);

        //reports event
        Events.report(new RecipeRegistered(recipe.id()));
    }

    public void updatePortion(@NotNull RecipeID recipeID, @NotNull Portion portion, boolean majorUpdate){

        //Validation
        if (!recipes().existsWithId(recipeID)){
            throw new IllegalArgumentException("There is not a recipe with id = " + recipeID.toString());
        }

        //loads the aggregate instance from DB
        Recipe recipe = recipes().withId(recipeID);

        //performs domain operation
        recipe.putOrUpdateItem(portion, majorUpdate);

        //persists
        recipes().update(recipe);

        //reports event
        Events.report(new RecipeUpdated(recipeID));
    }

}
