package com.capgemini.engineering.ddd.frozen_food.production;

import com.capgemini.engineering.ddd.frozen_food.production.DAO.DemandaDAO;
import com.capgemini.engineering.ddd.frozen_food.production.DAO.ProductionIngredientDAO;
import com.capgemini.engineering.ddd.frozen_food.production.DAO.PProductionOrderDAO;
import com.capgemini.engineering.ddd.frozen_food.production.DAO.RecipeDAO;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Producao implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static RecipeDAO recipeDAO(){
        return applicationContext.getBean(RecipeDAO.class);
    }

    public static ProductionIngredientDAO ingredientDAO(){
        return applicationContext.getBean(ProductionIngredientDAO.class);
    }

    public static PProductionOrderDAO productionOrderDAO() { return applicationContext.getBean(PProductionOrderDAO.class);}

    public static DemandaDAO demandaDAO() { return applicationContext.getBean(DemandaDAO.class);}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (Producao.applicationContext == null){
            Producao.applicationContext = applicationContext;
        }
    }
}
