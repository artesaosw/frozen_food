package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.dao.IngredientDAO;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IngredientsImpl implements Ingredients {

    @Autowired
    IngredientDAO ingredientDAO;

    @Override
    public List<Ingredient> all() {
        return ingredientDAO.getIngredients();
    }

    @Override
    public Ingredient withId(IngredientID id) {
        return ingredientDAO.getIngredientById(id);
    }

    @Override
    public boolean existsWithId(IngredientID id) {
        // TODO
        return false;
    }

    @Override
    public void registerNew(Ingredient aggregateRoot) {
        ingredientDAO.addIngredient(aggregateRoot);
    }

    @Override
    public void update(Ingredient aggregateRoot) {
        ingredientDAO.updateIngredient(aggregateRoot);
    }

    @Override
    public boolean existsWithName(String name) {
        // TODO
        return false;
    }

    @Override
    public boolean existsMinimumStock(String name) {
        // TODO
        return false;
    }

    @Override
    public boolean delete(IngredientID id) {
        return ingredientDAO.deleteIngredient(withId(id));
    }
}
