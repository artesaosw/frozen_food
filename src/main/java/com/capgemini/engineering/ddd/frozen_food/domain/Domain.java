package com.capgemini.engineering.ddd.frozen_food.domain;

import com.capgemini.engineering.ddd.frozen_food.domain.menu.Recipes;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.repository.Orders;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.repository.Ingredients;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.repository.Suppliers;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Domain implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static Recipes recipes(){
        return applicationContext.getBean(Recipes.class);
    }

    public static Orders orders() {
        return applicationContext.getBean(Orders.class);
    }

    public static Ingredients ingredients() {
        return applicationContext.getBean(Ingredients.class);
    }

    public static Suppliers suppliers() {
        return applicationContext.getBean(Suppliers.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (Domain.applicationContext == null){
            Domain.applicationContext = applicationContext;
        }
    }
}
