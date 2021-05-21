package com.capgemini.engineering.ddd.frozen_food.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;

public interface Ingredients extends Repository<Ingredient, IngredientID> {

}
