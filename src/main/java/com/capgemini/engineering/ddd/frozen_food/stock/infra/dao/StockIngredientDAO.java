package com.capgemini.engineering.ddd.frozen_food.stock.infra.dao;

import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.IngredientStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StockIngredientDAO extends MongoRepository<Ingredient, IngredientID> {

    boolean existsByName(String name);

    Ingredient findByName(String name);

    List<Ingredient> findAllByIngredientStatus(IngredientStatus ingredientStatus);

    @Query("{$expr:{$lte:['$ingredientStock', '$minimumStockValue']}}")
    List<Ingredient> findAllBelowMinimumStock();
}
