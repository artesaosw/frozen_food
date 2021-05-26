package com.capgemini.engineering.ddd.frozen_food.domain.producao.infra.Handler;

import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.exceptions.AlreadyExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.repository.Ingredients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class IngredientAddedHandler {

    @Autowired
    Ingredients ingredients;

    //Faltam Classes do shared
    @EventListener
    public void registerNewIngredient(NewIngredientRegisteredEvent newIngredientRegisteredEvent){
        IngredientDTO ingredientDTO = newIngredientRegisteredEvent.getIngredientDTO();
        Ingredient ingredient = ingredientDTO2ingredient(ingredientDTO);
        if(ingredients.existsByID(ingredientDTO.id){
            throw AlreadyExistentEntityException("There already exists an ingredient with this id")
        }


    }


}
