package com.capgemini.engineering.ddd.frozen_food.domain.stock.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RequirementID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.entity.Requirement;

import javax.validation.constraints.NotBlank;

public interface Requirements extends Repository<Requirement, RequirementID> {

    boolean existsWithName(@NotBlank String name);

    boolean existsMinimumStock(@NotBlank String name);
}
