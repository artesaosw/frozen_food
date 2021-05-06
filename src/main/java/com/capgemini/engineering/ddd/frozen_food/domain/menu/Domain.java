package com.capgemini.engineering.ddd.frozen_food.domain.menu;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Domain implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static Ingredients ingredients() {
        return applicationContext.getBean(Ingredients.class);
    }

    public static Recipes recipes() {
        return applicationContext.getBean(Recipes.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (Domain.applicationContext == null){
            Domain.applicationContext = applicationContext;
        }
    }
}
