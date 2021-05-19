package com.capgemini.engineering.ddd.frozen_food.domain.producao;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food._shared.BatchID;

import javax.validation.constraints.NotBlank;

public interface ProducedRecipes extends Repository<ProducedRecipe, BatchID> {

    boolean existsWithName(@NotBlank String name);
}

