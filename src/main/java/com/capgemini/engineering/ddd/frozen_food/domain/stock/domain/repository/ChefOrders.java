package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ChefOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.ChefOrder;

import javax.validation.constraints.NotBlank;

public interface ChefOrders extends Repository<ChefOrder, ChefOrderID> {

    boolean existsWithOrderReference(@NotBlank String orderReference);
}
