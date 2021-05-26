package com.capgemini.engineering.ddd.frozen_food.production.service;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.production.DAO.ProductionIngredientDAO;
import com.capgemini.engineering.ddd.frozen_food.production.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.production.exceptions.AlreadyExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.production.exceptions.NonExistentEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class MaintainIngredient implements DomainServices {

    @Autowired
    ProductionIngredientDAO productionIngredientDAO;

    public Ingredient getIngredientById(@NotNull IngredientID id){
        return productionIngredientDAO.findById(id).get();
    }

    public Ingredient getIngredientByDescription(@NotBlank String description) throws NonExistentEntityException {
        if (!productionIngredientDAO.existsByDescription(description)) {
            throw new NonExistentEntityException("There is no ingredient with name = " + description);
        }
        return productionIngredientDAO.findByDescription(description);
    }

    public List<Ingredient> getAllIngredients(){
        return productionIngredientDAO.findAll();
    }

    public void registerNew(@NotNull Ingredient ingredient) throws AlreadyExistentEntityException {
        if (productionIngredientDAO.existsByDescription(ingredient.getDescription())) {
            throw new AlreadyExistentEntityException("There Already exists and Ingredient name" + ingredient.getDescription());
        }
        productionIngredientDAO.save(ingredient);
    }

    public void updateIngredient(@NotNull Ingredient ingredient) throws NonExistentEntityException{
        if (!productionIngredientDAO.existsById(ingredient.getId())) {
            throw new NonExistentEntityException("There is no ingredient with name = " + ingredient.getDescription());
        }
        productionIngredientDAO.save(ingredient);
    };

    public void deleteIngredient(IngredientID id) throws NonExistentEntityException{
        if (!productionIngredientDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no ingredient with this id");
        }
        Ingredient ingredient = productionIngredientDAO.findById(id).get();
        productionIngredientDAO.delete(ingredient);
    };

}
