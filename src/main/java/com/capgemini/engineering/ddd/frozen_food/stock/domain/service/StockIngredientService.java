package com.capgemini.engineering.ddd.frozen_food.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.IngredientStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.StockIngredientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Service
public class StockIngredientService implements DomainServices {

    @Autowired
    StockIngredientDAO ingredientDAO;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public Ingredient getIngredientById(@NotNull IngredientID id) {
        if (!ingredientDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        return ingredientDAO.findById(id).get();
    }

    public Ingredient getIngredientByName(@NotBlank String name) {
        if (!ingredientDAO.existsByName(name)) {
            throw new NonExistentEntityException("There is no ingredient with name = " + name);
        }
        return ingredientDAO.findByName(name);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientDAO.findAll();
    }

    public List<Ingredient> getAllIngredientsByIngredientStatus(@NotNull IngredientStatus ingredientStatus) {
        return ingredientDAO.findAllByIngredientStatus(ingredientStatus);
    }

    public List<Ingredient> getAllIngredientsByBelowMinimumStock() {
        return ingredientDAO.findAllBelowMinimumStock();
    }

    public void registerNewIngredient(@NotNull Ingredient ingredient) {
        if (ingredientDAO.existsById(ingredient.id())) {
            // TODO Event to report problem to menu domain
            throw new DuplicatedEntityException("Already exists another ingredient with the same id.");
        }
        if (ingredientDAO.existsByName(ingredient.getName())) {
            // TODO Event to report problem to menu domain
            throw new DuplicatedEntityException("Already exists another ingredient with the same name.");
        }
        ingredientDAO.save(ingredient);
    }

    public void updateIngredient(@NotNull Ingredient ingredient){
        if (!ingredientDAO.existsById(ingredient.id())) {
            throw new NonExistentEntityException("There is no ingredient with id = " + ingredient.id());
        }
        ingredientDAO.save(ingredient);
    }

    public void updateIngredientUseStatus(@NotNull IngredientID id, @NotNull IngredientStatus ingredientStatus) {
        if (!ingredientDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(id).get();
        ingredient.setIngredientStatus(ingredientStatus);
        ingredientDAO.save(ingredient);
    }

    public void updateIngredientMinimumStockValue(@NotNull IngredientID id, @NotNull @PositiveOrZero Integer minimumStockValue) {
        if (!ingredientDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(id).get();
        ingredient.setMinimumStockValue(minimumStockValue);
        ingredientDAO.save(ingredient);
    }

    public void increaseIngredientStock(@NotNull IngredientID id, @NotNull @PositiveOrZero Integer quantity) {
        if (!ingredientDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(id).get();
        ingredient.increaseIngredientStock(quantity);
        ingredientDAO.save(ingredient);
    }

    public void decreaseIngredientStock(@NotNull IngredientID id, @NotNull @PositiveOrZero Integer quantity) {
        if (!ingredientDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(id).get();
        ingredient.decreaseIngredientStock(quantity);
        ingredientDAO.save(ingredient);
    }

    public void deleteIngredient(@NotNull IngredientID id) {
        if (!ingredientDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(id).get();
        ingredientDAO.delete(ingredient);
    }
}
