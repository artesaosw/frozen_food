package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Events;
import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.IngredientRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.IngredientStockUpdate;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.IngredientUpdate;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.IngredientStatus;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository.Ingredients;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class IngredientsService implements DomainServices {

    private Ingredients ingredients() {
        return Domain.ingredients();
    }

    public Ingredient getIngredientById(@NotNull IngredientID id) {
        Ingredient ingredient = null;
        // TODO
        return ingredient;
    }

    public Ingredient getIngredientByName(@NotBlank String name) {
        Ingredient ingredient = null;
        // TODO
        return ingredient;
    }

    public List<Ingredient> getAllIngredients() {
        return ingredients().all();
    }

    public void registerNewIngredient(@NotNull Ingredient ingredient) {
        if (ingredients().existsWithName(ingredient.getName())) {
            throw new IllegalArgumentException("Already exists another requirement with the same name.");
        }
        ingredients().registerNew(ingredient);
        Events.report(new IngredientRegistered(ingredient.id()));
    }

    public void updateIngredient(@NotNull Ingredient ingredient){
        if (!ingredients().existsWithId(ingredient.getId())) {
            throw new IllegalArgumentException("There is no ingredient with id = " + ingredient.getId().toString());
        }
        ingredients().update(ingredient);
        Events.report(new IngredientUpdate(ingredient.id()));
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

    public void deleteIngredient(@NotNull IngredientID ingredientID) {
        if (!ingredients().existsWithId(ingredientID)) {
            throw new IllegalArgumentException("There is no ingredient with id = " + ingredientID.toString());
        }
        ingredients().delete(ingredientID);
    }
}
