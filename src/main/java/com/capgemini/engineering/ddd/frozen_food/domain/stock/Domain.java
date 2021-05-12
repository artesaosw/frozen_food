package com.capgemini.engineering.ddd.frozen_food.domain.stock;

import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository.Orders;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository.Ingredients;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository.Suppliers;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Domain implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

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
