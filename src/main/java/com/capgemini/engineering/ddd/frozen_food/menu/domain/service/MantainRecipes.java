package com.capgemini.engineering.ddd.frozen_food.menu.domain.service;

import com.capgemini.engineering.ddd.frozen_food.menu.Menu;
import com.capgemini.engineering.ddd.frozen_food.Events;
import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.RecipeID;
import com.capgemini.engineering.ddd.frozen_food._shared.menu.dto.RecipeDTO;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.converter.RecipeConverter;
import com.capgemini.engineering.ddd.frozen_food._shared.menu.events.RecipeCatalogUpdatedPublisher;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.valueObject.Portion;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Recipe;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.RecipeUpdated;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.repository.Recipes;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class MantainRecipes implements DomainServices {

    private Recipes recipes(){
        return Menu.recipes();
    }

    public void registerNew(@NotBlank String name, @NotEmpty Set<Portion> items, @NotBlank String procedure, boolean integratesCatalog){

        //Validation
        if (recipes().existsWithName(name)){
            throw new IllegalArgumentException("There is already exists another recipe with the same name.");
        }

        //Instantiate aggregate
        Recipe recipe = new Recipe(name, items, procedure, integratesCatalog);

        //persists
        recipes().registerNew(recipe);

        //reports event
        RecipeDTO recipeDTO = RecipeConverter.recipe2RecipeDTO(recipe);

        if(integratesCatalog){
            RecipeCatalogUpdatedPublisher eventPublisher = new
                    RecipeCatalogUpdatedPublisher();

            eventPublisher.publishEvent(recipeDTO);
        }
    }

    public void updatePortion(@NotNull RecipeID recipeID, @NotNull Portion portion, boolean majorUpdate){

        //Validation
        if (!recipes().existsWithId(recipeID)){
            throw new IllegalArgumentException("There is not exists a recipe with id = " + recipeID.toString());
        }

        //loads the aggregate instance from DB
        Recipe recipe = recipes().withId(recipeID);

        //performs domain operation
        recipe.putOrUpdateItem(portion, majorUpdate);

        //persists
        recipes().update(recipe);

        //reports event
        Events.report(new RecipeUpdated(recipeID, recipe));
    }

}
