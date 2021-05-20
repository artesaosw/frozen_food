package com.capgemini.engineering.ddd.frozen_food.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.Events;
import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.stock.Stock;
import com.capgemini.engineering.ddd.frozen_food._shared.stock.event.IngredientStockUpdated;
import com.capgemini.engineering.ddd.frozen_food._shared.stock.event.IngredientUpdated;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.IngredientStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.repository.Ingredients;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.IngredientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Service
public class IngredientService implements DomainServices {

    @Autowired
    IngredientDAO ingredientDAO;

    private Ingredients ingredients() {
        return Stock.ingredients();
    }

    public Ingredient getIngredientByIngredientID(@NotNull IngredientID id) {
        return ingredientDAO.findByIngredientID(id);
    }

    public Ingredient getIngredientByName(@NotBlank String name) {
        return ingredientDAO.findByName(name);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientDAO.findAll();
    }

    public List<Ingredient> getAllIngredientsByIngredientStatus(@NotNull IngredientStatus ingredientStatus) {
        return ingredientDAO.findAllByIngredientStatus(ingredientStatus);
    }

    public void registerNewIngredient(@NotNull Ingredient ingredient) {
        if (ingredientDAO.existsByIngredientID(ingredient.id())) {
            throw new DuplicatedEntityException("Already exists another ingredient with the same id.");
        }
        if (ingredientDAO.existsByName(ingredient.getName())) {
            throw new DuplicatedEntityException("Already exists another ingredient with the same name.");
        }
        ingredientDAO.save(ingredient);
    }

    public void updateIngredient(@NotNull Ingredient ingredient){
        if (!ingredientDAO.existsByIngredientID(ingredient.id())) {
            throw new NonExistentEntityException("There is no ingredient with id = " + ingredient.id());
        }
        ingredientDAO.save(ingredient);
        Events.report(new IngredientUpdated(ingredient.id()));
    }

    public void updateIngredientUseStatus(@NotNull IngredientID id, @NotNull IngredientStatus ingredientStatus) {
        if (!ingredientDAO.existsByIngredientID(id)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findByIngredientID(id);
        ingredient.setIngredientStatus(ingredientStatus);
        ingredientDAO.save(ingredient);
        Events.report(new IngredientUpdated(ingredient.id()));
    }

    public void updateIngredientMinimumStockValue(@NotNull IngredientID id, @NotNull @PositiveOrZero Integer minimumStockValue) {
        if (!ingredientDAO.existsByIngredientID(id)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findByIngredientID(id);
        ingredient.setMinimumStockValue(minimumStockValue);
        ingredientDAO.save(ingredient);
        Events.report(new IngredientUpdated(ingredient.id()));
    }

    public void increaseIngredientStock(@NotNull IngredientID id, @NotNull @PositiveOrZero Integer quantity) {
        if (!ingredientDAO.existsByIngredientID(id)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findByIngredientID(id);
        ingredient.increaseIngredientStock(quantity);
        ingredientDAO.save(ingredient);
        Events.report(new IngredientStockUpdated(ingredient.id()));
    }

    public void decreaseIngredientStock(@NotNull IngredientID id, @NotNull @PositiveOrZero Integer quantity) {
        if (!ingredientDAO.existsByIngredientID(id)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findByIngredientID(id);
        ingredient.decreaseIngredientStock(quantity);
        ingredientDAO.save(ingredient);
        Events.report(new IngredientStockUpdated(ingredient.id()));
    }

    public void deleteIngredient(@NotNull IngredientID id) {
        if (!ingredientDAO.existsByIngredientID(id)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findByIngredientID(id);
        ingredientDAO.delete(ingredient);
    }
}
