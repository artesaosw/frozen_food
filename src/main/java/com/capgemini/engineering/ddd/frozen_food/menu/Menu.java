package com.capgemini.engineering.ddd.frozen_food.menu;

import com.capgemini.engineering.ddd.frozen_food.menu.domain.repository.Ingredients;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.repository.Orders;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.repository.Recipes;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Menu implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static Recipes recipes(){
        return applicationContext.getBean(Recipes.class);
    }

    public static Ingredients ingredients(){
        return applicationContext.getBean(Ingredients.class);
    }

    public static Orders orders() { return applicationContext.getBean(Orders.class); }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (Menu.applicationContext == null){
            Menu.applicationContext = applicationContext;
        }
    }
}
