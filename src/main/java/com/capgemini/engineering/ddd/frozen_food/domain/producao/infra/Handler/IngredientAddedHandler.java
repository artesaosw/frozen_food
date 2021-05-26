package com.capgemini.engineering.ddd.frozen_food.domain.producao.infra.Handler;

import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.DAO.IngredientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class IngredientAddedHandler {

    @Autowired
    IngredientDAO ingredientDAO;

    //@EventListener
    //public void registerNewIngredient(NewIngredientRegisteredEvent newIngredientRegisteredEvent){
    //    }


    }



