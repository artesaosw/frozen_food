package com.capgemini.engineering.ddd.frozen_food.domain;

import com.capgemini.engineering.ddd.frozen_food.domain.menu.Ingredients;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.Recipes;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.repository.Demandas;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.repository.ProductionOrders;
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

    public static ProductionOrders productionOrders() { return applicationContext.getBean(ProductionOrders.class);}

    public static Demandas demandas() { return applicationContext.getBean(Demandas.class);}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (Producao.applicationContext == null){
            Producao.applicationContext = applicationContext;
        }
    }
}
