package com.capgemini.engineering.ddd.frozen_food.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.Events;
import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.stock.Stock;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.event.IngredientStockUpdated;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.event.IngredientUpdated;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.IngredientStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.repository.Ingredients;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.IngredientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService implements DomainServices {

    @Autowired
    IngredientDAO ingredientDAO;

    private Ingredients ingredients() {
        return Stock.ingredients();
    }

    public Ingredient getIngredientById(@NotNull IngredientID id) {
        Optional<Ingredient> ingredient = ingredientDAO.findById(id);
        return ingredient.get();
    }

    public Ingredient getIngredientByName(@NotBlank String name) {
        Optional<Ingredient> ingredient = ingredientDAO.findByName(name);
        return ingredient.get();
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientDAO.findAll();
    }

    public List<Ingredient> getAllIngredientsByIngredientStatus(IngredientStatus ingredientStatus) {
        return ingredientDAO.findAllByIngredientStatus(ingredientStatus);
    }

    public void registerNewIngredient(@NotNull Ingredient ingredient) {
        if (ingredientDAO.existsByName(ingredient.getName())) {
            throw new IllegalArgumentException("Already exists another ingredient with the same name.");
        }
        ingredientDAO.save(ingredient);
    }

    public void updateIngredient(@NotNull Ingredient ingredient){
        if (!ingredientDAO.existsById(ingredient.getId())) {
            throw new IllegalArgumentException("There is no ingredient with id = " + ingredient.getId().toString());
        }
        ingredientDAO.save(ingredient);
        Events.report(new IngredientUpdated(ingredient.id()));
    }

    public void updateIngredientUseStatus(@NotNull IngredientID id, @NotNull IngredientStatus ingredientStatus) {
        if (!ingredientDAO.existsById(id)) {
            throw new IllegalArgumentException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(id).get();
        ingredient.setIngredientStatus(ingredientStatus);
        ingredientDAO.save(ingredient);
        Events.report(new IngredientUpdated(ingredient.id()));
    }

    public void updateIngredientMinimumStockValue(@NotNull IngredientID id, @NotNull @PositiveOrZero Integer minimumStockValue) {
        if (!ingredientDAO.existsById(id)) {
            throw new IllegalArgumentException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(id).get();
        ingredient.setMinimumStockValue(minimumStockValue);
        ingredientDAO.save(ingredient);
        Events.report(new IngredientUpdated(ingredient.id()));
    }

    public void increaseIngredientStock(@NotNull IngredientID id, @NotNull @PositiveOrZero Integer quantity) {
        if (!ingredientDAO.existsById(id)) {
            throw new IllegalArgumentException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(id).get();
        ingredient.increaseIngredientStock(quantity);
        ingredientDAO.save(ingredient);
        Events.report(new IngredientStockUpdated(ingredient.id()));
    }

    public void decreaseIngredientStock(@NotNull IngredientID id, @NotNull @PositiveOrZero Integer quantity) {
        if (!ingredientDAO.existsById(id)) {
            throw new IllegalArgumentException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(id).get();
        ingredient.decreaseIngredientStock(quantity);
        ingredientDAO.save(ingredient);
        Events.report(new IngredientStockUpdated(ingredient.id()));
    }

    public void deleteIngredient(@NotNull IngredientID id) {
        if (!ingredientDAO.existsById(id)) {
            throw new IllegalArgumentException("There is no ingredient with id = " + id);
        }
        Optional<Ingredient> ingredient = ingredientDAO.findById(id);
        ingredientDAO.delete(ingredient.get());
    }
}
