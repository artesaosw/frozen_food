package com.capgemini.engineering.ddd.frozen_food.domain.producao.service;


import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.DAO.IngredientDAO;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.exceptions.AlreadyExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.exceptions.NonExistentEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class MaintainIngredient implements DomainServices {

    @Autowired
    IngredientDAO ingredientDAO;

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

    public void registerNew(@NotNull Ingredient ingredient) throws AlreadyExistentEntityException{
        if (ingredientDAO.existsByDescription(ingredient.getDescription())) {
            throw new AlreadyExistentEntityException("There Already exists and Ingredient name" + ingredient.getDescription());
        }
        ingredientDAO.save(ingredient);
    }

    public void updateIngredient(@NotNull Ingredient ingredient) throws NonExistentEntityException{
        if (!ingredientDAO.existsById(ingredient.getId())) {
            throw new NonExistentEntityException("There is no ingredient with name = " + ingredient.getDescription());
        }
        ingredientDAO.save(ingredient);
    };

    public void deleteIngredient(IngredientID id) throws NonExistentEntityException{
        if (!ingredientDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no ingredient with this id");
        }
        Ingredient ingredient = ingredientDAO.findById(id).get();
        ingredientDAO.delete(ingredient);
    };

}
