package com.capgemini.engineering.ddd.frozen_food.domain.producao.DAO;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;


public interface IngredientDAO extends MongoRepository<Ingredient, IngredientID> {

    boolean existsByDescription(String description);

    Ingredient findByDescription(String description);

}
