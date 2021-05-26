package com.capgemini.engineering.ddd.frozen_food.production.infra.Handler;

import com.capgemini.engineering.ddd.frozen_food.production.DAO.ProductionIngredientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientAddedHandler {

    @Autowired
    ProductionIngredientDAO productionIngredientDAO;

    //@EventListener
    //public void registerNewIngredient(NewIngredientRegisteredEvent newIngredientRegisteredEvent){
    //    }


    }



