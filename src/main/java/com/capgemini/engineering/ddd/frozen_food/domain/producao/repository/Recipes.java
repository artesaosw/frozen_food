package com.capgemini.engineering.ddd.frozen_food.domain.producao.repository;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.RecipeID;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.validation.constraints.NotBlank;


public interface Recipes extends MongoRepository<Recipe, RecipeID> {

    boolean existsWithName(@NotBlank String name);
}
