package com.capgemini.engineering.ddd.frozen_food.domain.stock;

import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Domain implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static SuppliersOrders suppliersOrders() {
        return applicationContext.getBean(SuppliersOrders.class);
    }

    public static ChefOrders chefOrders() {
        return applicationContext.getBean(ChefOrders.class);
    }

    public static ProductionOrders productionOrders() {
        return applicationContext.getBean(ProductionOrders.class);
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
