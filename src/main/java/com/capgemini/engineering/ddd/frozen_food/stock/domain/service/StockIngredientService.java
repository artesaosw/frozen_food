package com.capgemini.engineering.ddd.frozen_food.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.IngredientStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.StockIngredientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Service
@Validated
public class StockIngredientService implements DomainServices {

    @Autowired
    StockIngredientDAO ingredientDAO;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public Ingredient getIngredientById(@NotBlank String id) {
        IngredientID ingredientID = Identificator.newInstance(IngredientID.class, id);
        if (!ingredientDAO.existsById(ingredientID)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        return ingredientDAO.findById(ingredientID).get();
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

    public void updateIngredient(@NotNull Ingredient ingredient) {
        if (!ingredientDAO.existsById(ingredient.id())) {
            throw new NonExistentEntityException("There is no ingredient with id = " + ingredient.id());
        }
        ingredientDAO.save(ingredient);
    }

    public void updateIngredientUseStatus(@NotBlank String id, @NotNull IngredientStatus ingredientStatus) {
        IngredientID ingredientID = Identificator.newInstance(IngredientID.class, id);
        if (!ingredientDAO.existsById(ingredientID)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(ingredientID).get();
        ingredient.setIngredientStatus(ingredientStatus);
        ingredientDAO.save(ingredient);
    }

    public void updateIngredientMinimumStockValue(@NotBlank String id, @NotNull @PositiveOrZero Integer minimumStockValue) {
        IngredientID ingredientID = Identificator.newInstance(IngredientID.class, id);
        if (!ingredientDAO.existsById(ingredientID)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(ingredientID).get();
        ingredient.setMinimumStockValue(minimumStockValue);
        ingredientDAO.save(ingredient);
    }

    public void increaseIngredientStock(@NotBlank String id, @NotNull @Positive Integer quantity) {
        IngredientID ingredientID = Identificator.newInstance(IngredientID.class, id);
        if (!ingredientDAO.existsById(ingredientID)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(ingredientID).get();
        ingredient.increaseIngredientStock(quantity);
        ingredientDAO.save(ingredient);
    }

    public void decreaseIngredientStock(@NotBlank String id, @NotNull @Positive Integer quantity) {
        IngredientID ingredientID = Identificator.newInstance(IngredientID.class, id);
        if (!ingredientDAO.existsById(ingredientID)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(ingredientID).get();
        ingredient.decreaseIngredientStock(quantity);
        ingredientDAO.save(ingredient);
    }

    public void deleteIngredient(@NotBlank String id) {
        IngredientID ingredientID = Identificator.newInstance(IngredientID.class, id);
        if (!ingredientDAO.existsById(ingredientID)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(ingredientID).get();
        ingredientDAO.delete(ingredient);
    }
}
