package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.ProductionOrder;

import javax.validation.constraints.NotBlank;

public interface ProductionOrders extends Repository<ProductionOrder, ProductionOrderID> {

    boolean existsWithOrderReference(@NotBlank String orderReference);
}
