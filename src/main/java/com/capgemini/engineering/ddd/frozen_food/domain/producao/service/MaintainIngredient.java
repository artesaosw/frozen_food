package com.capgemini.engineering.ddd.frozen_food.domain.producao.service;


import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.exceptions.AlreadyExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.exceptions.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.repository.Ingredients;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class MaintainIngredient implements DomainServices {

    @Autowired
    Ingredients ingredients;

    public Ingredient getIngredientById(@NotNull IngredientID id){
        return ingredients.findById(id).get();
    }

    public Ingredient getIngredientByDescription(@NotBlank String description) throws NonExistentEntityException {
        if (!ingredients.existsByDescription(description)) {
            throw new NonExistentEntityException("There is no ingredient with name = " + description);
        }
        return ingredients.findByDescription(description);
    }

    public List<Ingredient> getAllIngredients(){
        return ingredients.findAll();
    }

    public void registerNew(@NotNull Ingredient ingredient) throws AlreadyExistentEntityException{
        if (ingredients.existsByDescription(ingredient.getDescription())) {
            throw new AlreadyExistentEntityException("There Already exists and Ingredient name" + ingredient.getDescription());
        }
        ingredients.save(ingredient);
    }

    public void updateIngredient(@NotNull Ingredient ingredient) throws NonExistentEntityException{
        if (!ingredients.existsById(ingredient.getId())) {
            throw new NonExistentEntityException("There is no ingredient with name = " + ingredient.getDescription());
        }
        ingredients.save(ingredient);
    };

    public void deleteIngredient(IngredientID id) throws NonExistentEntityException{
        if (!ingredients.existsById(id)) {
            throw new NonExistentEntityException("There is no ingredient with this id");
        }
        Ingredient ingredient = ingredients.findById(id).get();
        ingredients.delete(ingredient);
    };

}
