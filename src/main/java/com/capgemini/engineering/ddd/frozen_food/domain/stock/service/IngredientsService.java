package com.capgemini.engineering.ddd.frozen_food.domain.stock.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.Events;
import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.IngredientRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.IngredientUpdate;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.IngredientStatus;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.repository.Ingredients;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class IngredientsService implements DomainServices {

    private Ingredients requirements() {
        return Domain.ingredients();
    }

    public void registerNewRequirement(@NotBlank String name, @NotNull Unit unit, Integer minimumStockValue) {
        if (requirements().existsWithName(name)) {
            throw new IllegalArgumentException("Already exists another requirement with the same name.");
        }
        Ingredient ingredient = new Ingredient(name, unit, minimumStockValue);
        requirements().registerNew(ingredient);
        Events.report(new IngredientRegistered(ingredient.id()));
    }

    public void updateRequirementUse(@NotNull IngredientID ingredientID, @NotNull IngredientStatus ingredientStatus) {
        if (!requirements().existsWithId(ingredientID)) {
            throw new IllegalArgumentException("There is no requirement with id = " + ingredientID.toString());
        }
        Ingredient ingredient = requirements().withId(ingredientID);
        ingredient.setIngredientStatus(ingredientStatus);
        requirements().update(ingredient);
        Events.report(new IngredientUpdate(ingredientID));
    }

    public void updateRequirementMinimumStockValue(@NotNull IngredientID ingredientID, @NotNull Integer minimumStockValue) {
        if (!requirements().existsWithId(ingredientID)) {
            throw new IllegalArgumentException("There is no requirement with id = " + ingredientID.toString());
        }
        Ingredient ingredient = requirements().withId(ingredientID);
        ingredient.setMinimumStockValue(minimumStockValue);
        requirements().update(ingredient);
        Events.report(new IngredientUpdate(ingredientID));
    }

    public void updateStockRequirementFromOrder() {
        // TODO
    }

    public void updateStockRequirementFromProduction() {
        // TODO
    }
}
