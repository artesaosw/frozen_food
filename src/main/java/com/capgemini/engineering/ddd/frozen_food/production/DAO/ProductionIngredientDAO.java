package com.capgemini.engineering.ddd.frozen_food.production.DAO;

import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.production.entity.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductionIngredientDAO extends MongoRepository<Ingredient, IngredientID> {

    boolean existsByDescription(String description);

    Ingredient findByDescription(String description);

}
