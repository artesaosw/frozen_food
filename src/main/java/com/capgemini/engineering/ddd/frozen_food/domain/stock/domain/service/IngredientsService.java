package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Events;
import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.IngredientRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.IngredientUpdate;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.IngredientStatus;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository.Ingredients;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class IngredientsService implements DomainServices {

    private Ingredients ingredients() {
        return Domain.ingredients();
    }

    public void registerNewRequirement(@NotBlank String name, @NotNull Unit unit, Integer minimumStockValue) {
        if (ingredients().existsWithName(name)) {
            throw new IllegalArgumentException("Already exists another requirement with the same name.");
        }
        Ingredient ingredient = new Ingredient(name, unit, minimumStockValue);
        ingredients().registerNew(ingredient);
        Events.report(new IngredientRegistered(ingredient.id()));
    }

    public void updateRequirementUse(@NotNull IngredientID ingredientID, @NotNull IngredientStatus ingredientStatus) {
        if (!ingredients().existsWithId(ingredientID)) {
            throw new IllegalArgumentException("There is no requirement with id = " + ingredientID.toString());
        }
        Ingredient ingredient = ingredients().withId(ingredientID);
        ingredient.setIngredientStatus(ingredientStatus);
        ingredients().update(ingredient);
        Events.report(new IngredientUpdate(ingredientID));
    }

    public void updateRequirementMinimumStockValue(@NotNull IngredientID ingredientID, @NotNull Integer minimumStockValue) {
        if (!ingredients().existsWithId(ingredientID)) {
            throw new IllegalArgumentException("There is no requirement with id = " + ingredientID.toString());
        }
        Ingredient ingredient = ingredients().withId(ingredientID);
        ingredient.setMinimumStockValue(minimumStockValue);
        ingredients().update(ingredient);
        Events.report(new IngredientUpdate(ingredientID));
    }

    public void updateStockRequirementFromOrder() {
        // TODO
    }

    public void updateStockRequirementFromProduction() {
        // TODO
    }
}
