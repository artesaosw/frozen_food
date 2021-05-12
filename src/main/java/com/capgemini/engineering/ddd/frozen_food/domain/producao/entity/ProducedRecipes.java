package com.capgemini.engineering.ddd.frozen_food.domain.producao.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.ProducedRecipe;

import javax.validation.constraints.NotBlank;

public interface ProducedRecipes extends Repository<ProducedRecipe, BatchID> {

    boolean existsWithName(@NotBlank String name);
}

