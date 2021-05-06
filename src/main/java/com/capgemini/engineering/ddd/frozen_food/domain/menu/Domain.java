package com.capgemini.engineering.ddd.frozen_food.domain.menu;

import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.repository.Ingredients;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.domain.repository.Recipes;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Domain implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static Recipes recipes(){
        return applicationContext.getBean(Recipes.class);
    }

    public static Ingredients ingredients(){
        return applicationContext.getBean(Ingredients.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (Domain.applicationContext == null){
            Domain.applicationContext = applicationContext;
        }
    }
}
