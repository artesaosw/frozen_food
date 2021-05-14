package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Events;
import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.IngredientRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.IngredientStockUpdate;
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

    public void registerNewIngredient(@NotBlank String name, @NotNull Unit unit, Integer minimumStockValue) {
        if (ingredients().existsWithName(name)) {
            throw new IllegalArgumentException("Already exists another requirement with the same name.");
        }
        Ingredient ingredient = new Ingredient(name, unit, minimumStockValue);
        ingredients().registerNew(ingredient);
        Events.report(new IngredientRegistered(ingredient.id()));
    }

    public void updateIngredientUseStatus(@NotNull IngredientID ingredientID, @NotNull IngredientStatus ingredientStatus) {
        if (!ingredients().existsWithId(ingredientID)) {
            throw new IllegalArgumentException("There is no ingredient with id = " + ingredientID.toString());
        }
        Ingredient ingredient = ingredients().withId(ingredientID);
        ingredient.setIngredientStatus(ingredientStatus);
        ingredients().update(ingredient);
        Events.report(new IngredientUpdate(ingredientID));
    }

    public void updateIngredientMinimumStockValue(@NotNull IngredientID ingredientID, @NotNull Integer minimumStockValue) {
        if (!ingredients().existsWithId(ingredientID)) {
            throw new IllegalArgumentException("There is no ingredient with id = " + ingredientID.toString());
        }
        Ingredient ingredient = ingredients().withId(ingredientID);
        ingredient.setMinimumStockValue(minimumStockValue);
        ingredients().update(ingredient);
        Events.report(new IngredientUpdate(ingredientID));
    }

    public void increaseIngredientStock(@NotNull IngredientID ingredientID, @NotNull Integer increaseIngredientStock) {
        if (!ingredients().existsWithId(ingredientID)) {
            throw new IllegalArgumentException("There is no ingredient with id = " + ingredientID.toString());
        }
        Ingredient ingredient = ingredients().withId(ingredientID);
        Integer currentIngredientStock = ingredient.getIngredientStock();
        Integer newIngredientStock = currentIngredientStock + increaseIngredientStock;
        ingredient.setIngredientStock(newIngredientStock);
        ingredients().update(ingredient);
        Events.report(new IngredientStockUpdate(ingredientID));
    }

    public void decreaseIngredientStock(@NotNull IngredientID ingredientID, @NotNull Integer decreaseIngredientStock) {
        if (!ingredients().existsWithId(ingredientID)) {
            throw new IllegalArgumentException("There is no ingredient with id = " + ingredientID.toString());
        }
        Integer newIngredientStock;
        Ingredient ingredient = ingredients().withId(ingredientID);
        Integer currentIngredientStock = ingredient.getIngredientStock();
        if(currentIngredientStock < decreaseIngredientStock) {
            newIngredientStock = 0; // TODO verificar
        } else {
            newIngredientStock = currentIngredientStock - decreaseIngredientStock;
        }
        ingredient.setIngredientStock(newIngredientStock);
        ingredients().update(ingredient);
        Events.report(new IngredientStockUpdate(ingredientID));
    }
}
