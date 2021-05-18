package com.capgemini.engineering.ddd.frozen_food.stock.infra.dao;

import com.capgemini.engineering.ddd.frozen_food._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.IngredientStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientDAO extends MongoRepository<Ingredient, IngredientID> {

    boolean existsByName(String name);

    Optional<Ingredient> findByName(String name);

    List<Ingredient> findAllByIngredientStatus(IngredientStatus ingredientStatus);
}
