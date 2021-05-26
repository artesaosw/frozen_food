package com.capgemini.engineering.ddd.frozen_food.domain;

import com.capgemini.engineering.ddd.frozen_food.domain.producao.DAO.DemandaDAO;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.DAO.IngredientDAO;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.DAO.ProductionOrderDAO;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.DAO.RecipeDAO;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Producao implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static RecipeDAO recipeDAO(){
        return applicationContext.getBean(RecipeDAO.class);
    }

    public static IngredientDAO ingredientDAO(){
        return applicationContext.getBean(IngredientDAO.class);
    }

    public static ProductionOrderDAO productionOrderDAO() { return applicationContext.getBean(ProductionOrderDAO.class);}

    public static DemandaDAO demandaDAO() { return applicationContext.getBean(DemandaDAO.class);}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (Producao.applicationContext == null){
            Producao.applicationContext = applicationContext;
        }
    }
}
