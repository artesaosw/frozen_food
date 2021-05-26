package com.capgemini.engineering.ddd.frozen_food.production.DAO;

import com.capgemini.engineering.ddd.frozen_food._shared.id.RecipeID;
import com.capgemini.engineering.ddd.frozen_food.production.entity.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.validation.constraints.NotBlank;

public interface RecipeDAO extends MongoRepository<Recipe, RecipeID> {

//    boolean existsWithName(@NotBlank String name);
}
