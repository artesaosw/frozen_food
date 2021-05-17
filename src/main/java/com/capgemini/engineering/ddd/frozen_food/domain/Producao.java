package com.capgemini.engineering.ddd.frozen_food.domain;

import com.capgemini.engineering.ddd.frozen_food.domain.menu.Ingredients;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.Recipes;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Demanda;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.ProducedRecipes;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Producao implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static Recipes recipes(){
        return applicationContext.getBean(Recipes.class);
    }

    public static Ingredients ingredients(){
        return applicationContext.getBean(Ingredients.class);
    }

    public static ProducedRecipes producedRecipes() { return applicationContext.getBean(ProducedRecipes.class);}

    public static Demanda demanda() { return applicationContext.getBean(Demanda.class);}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (Producao.applicationContext == null){
            Producao.applicationContext = applicationContext;
        }
    }
}
