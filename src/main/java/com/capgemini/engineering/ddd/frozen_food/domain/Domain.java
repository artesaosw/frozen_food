package com.capgemini.engineering.ddd.frozen_food.domain;

import com.capgemini.engineering.ddd.frozen_food.domain.menu.Ingredients;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.Recipes;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.Customers;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.Orders;
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

    public static Customers customers(){
        return applicationContext.getBean(Customers.class);
    }

    public static Orders orders(){
        return applicationContext.getBean(Orders.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (Domain.applicationContext == null){
            Domain.applicationContext = applicationContext;
        }
    }
}
