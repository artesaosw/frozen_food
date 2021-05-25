package com.capgemini.engineering.ddd.frozen_food.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.Events;
import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.IngredientDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.event.menu_stock.NewIngredientRegisteredEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.event.IngredientStockUpdated;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.event.IngredientUpdated;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.IngredientStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.IngredientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

import static com.capgemini.engineering.ddd.frozen_food.stock.domain.converter.IngredientConverter.ingredientDTO2Ingredient;

@Service
public class IngredientService implements DomainServices {

    @Autowired
    IngredientDAO ingredientDAO;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

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

    @EventListener
    public void registerNewIngredient(NewIngredientRegisteredEvent newIngredientRegisteredEvent) {
        IngredientDTO ingredientDTO = newIngredientRegisteredEvent.getIngredientDTO();
        Ingredient ingredient = ingredientDTO2Ingredient(ingredientDTO);
        registerNewIngredient(ingredient);
    }

    public void updateIngredient(@NotNull Ingredient ingredient){
        if (!ingredientDAO.existsById(ingredient.id())) {
            throw new NonExistentEntityException("There is no ingredient with id = " + ingredient.id());
        }
        ingredientDAO.save(ingredient);
        Events.report(new IngredientUpdated(ingredient.id()));
    }

    public void updateIngredientUseStatus(@NotNull IngredientID id, @NotNull IngredientStatus ingredientStatus) {
        if (!ingredientDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(id).get();
        ingredient.setIngredientStatus(ingredientStatus);
        ingredientDAO.save(ingredient);
        Events.report(new IngredientUpdated(ingredient.id()));
    }

    public void updateIngredientMinimumStockValue(@NotNull IngredientID id, @NotNull @PositiveOrZero Integer minimumStockValue) {
        if (!ingredientDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(id).get();
        ingredient.setMinimumStockValue(minimumStockValue);
        ingredientDAO.save(ingredient);
        Events.report(new IngredientUpdated(ingredient.id()));
    }

    public void increaseIngredientStock(@NotNull IngredientID id, @NotNull @PositiveOrZero Integer quantity) {
        if (!ingredientDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(id).get();
        ingredient.increaseIngredientStock(quantity);
        ingredientDAO.save(ingredient);
        Events.report(new IngredientStockUpdated(ingredient.id()));
    }

    public void decreaseIngredientStock(@NotNull IngredientID id, @NotNull @PositiveOrZero Integer quantity) {
        if (!ingredientDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(id).get();
        ingredient.decreaseIngredientStock(quantity);
        ingredientDAO.save(ingredient);
        Events.report(new IngredientStockUpdated(ingredient.id()));
    }

    public void deleteIngredient(@NotNull IngredientID id) {
        if (!ingredientDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(id).get();
        ingredientDAO.delete(ingredient);
    }
}
