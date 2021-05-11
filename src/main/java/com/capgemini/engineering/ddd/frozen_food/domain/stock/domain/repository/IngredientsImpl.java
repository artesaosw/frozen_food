package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.Ingredient;

import java.util.List;

public class IngredientsImpl implements Ingredients {
    @Override
    public List<Ingredient> all() {
        // TODO
        return null;
    }

    @Override
    public Ingredient withId(IngredientID id) {
        // TODO
        return null;
    }

    @Override
    public boolean existsWithId(IngredientID id) {
        // TODO
        return false;
    }

    @Override
    public void registerNew(Ingredient aggregateRoot) {
        // TODO
    }

    @Override
    public void update(Ingredient aggregateRoot) {
        // TODO
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
}
