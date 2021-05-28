package com.capgemini.engineering.ddd.frozen_food.menu.infra.dao;

import com.capgemini.engineering.ddd.frozen_food._shared.id.RecipeID;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeDAO extends MongoRepository<Recipe, RecipeID> {

}
