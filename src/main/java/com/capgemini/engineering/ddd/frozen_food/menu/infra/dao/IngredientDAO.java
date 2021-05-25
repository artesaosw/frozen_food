package com.capgemini.engineering.ddd.frozen_food.menu.infra.dao;

import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IngredientDAO extends MongoRepository<Ingredient, IngredientID> {

    boolean existsByDescription(String description);

    Ingredient findByDescription(String description);

}
