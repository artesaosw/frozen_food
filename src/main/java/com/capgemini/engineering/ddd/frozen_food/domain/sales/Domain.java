package com.capgemini.engineering.ddd.frozen_food.domain.sales;

import com.capgemini.engineering.ddd.frozen_food.domain.menu.Ingredients;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.Recipes;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.ProductionOrder;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository.Customers;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository.Orders;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository.ProductionOrders;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository.Products;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Domain implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static Customers customers(){
        return applicationContext.getBean(Customers.class);
    }

    public static Orders orders(){
        return applicationContext.getBean(Orders.class);
    }

    public static Products products(){
        return applicationContext.getBean(Products.class);
    }

    public static ProductionOrders productionOrders(){
        return applicationContext.getBean(ProductionOrders.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (Domain.applicationContext == null){
            Domain.applicationContext = applicationContext;
        }
    }
}
