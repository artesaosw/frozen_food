package com.capgemini.engineering.ddd.frozen_food.sales;

import com.capgemini.engineering.ddd.frozen_food.sales.domain.repository.Customers;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.repository.Orders;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.repository.ProductionOrders;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.repository.Products;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Sales implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static ProductionOrders getProductionOrders() {
        return applicationContext.getBean(ProductionOrders.class);
    }

    public static Products products() {
        return applicationContext.getBean(Products.class);
    }

    public static Customers customers(){
        return applicationContext.getBean(Customers.class);
    }

    public static Orders orders(){
        return applicationContext.getBean(Orders.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (Sales.applicationContext == null){
            Sales.applicationContext = applicationContext;
        }
    }
}
