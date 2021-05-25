package com.capgemini.engineering.ddd.frozen_food.menu.domain.service;

import com.capgemini.engineering.ddd.frozen_food.Events;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.menu.Menu;
import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.IngredientRegistered;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.repository.Ingredients;
import com.capgemini.engineering.ddd.frozen_food.menu.infra.dao.IngredientDAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class MantainIngredients implements DomainServices {

    @Autowired
    IngredientDAO ingredientDAO;

    private Ingredients ingredients() { return Menu.ingredients(); }

    public Ingredient getIngredientById(@NotNull IngredientID id){
        return ingredientDAO.findById(id).get();
    }

    public Ingredient getIngredientByDescription(@NotBlank String description) throws NonExistentEntityException {
        if (!ingredientDAO.existsByDescription(description)) {
            throw new NonExistentEntityException("There is no ingredient with name = " + description);
        }
        return ingredientDAO.findByDescription(description);
    }

    public List<Ingredient> getAllIngredients(){
        return ingredientDAO.findAll();
    }

    public void registerNew(@NotBlank String description) throws DuplicatedEntityException {

        //Validation
        if(ingredientDAO.existsByDescription(description)){
            throw new DuplicatedEntityException("It already exists another ingredient with the same description.");
        }

        //Instantiate aggregate
        Ingredient ingredient = new Ingredient(description);

        //persists
        //ingredients().registerNew(ingredient);        --> já não é necessário?
        ingredientDAO.save(ingredient);

        Events.report(new IngredientRegistered (ingredient.getId()));
    }

    public void registerNew(@NotNull Ingredient ingredient) throws DuplicatedEntityException {
        if (ingredientDAO.existsById(ingredient.getId())) {
            throw new DuplicatedEntityException("Already exists another ingredient with the same id.");
        }
        if (ingredientDAO.existsByDescription(ingredient.getDescription())) {
            throw new DuplicatedEntityException("Already exists another ingredient with the same name.");
        }
        ingredientDAO.save(ingredient);
    }

    public void updateIngredient(Ingredient ingredient) throws NonExistentEntityException {
        if (!ingredientDAO.existsById(ingredient.getId())) {
            throw new NonExistentEntityException("There is no ingredient with id = " + ingredient.id());
        }
        ingredientDAO.save(ingredient);
    }

    public void deleteIngredient(@NotNull IngredientID id) throws NonExistentEntityException {
        if (!ingredientDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no ingredient with id = " + id);
        }
        Ingredient ingredient = ingredientDAO.findById(id).get();
        ingredientDAO.delete(ingredient);
    }

}
