package com.capgemini.engineering.ddd.frozen_food.domain.producao.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.ProducedRecipe;

public interface ProducedRecipes extends Repository<ProducedRecipe, BatchID> {
}