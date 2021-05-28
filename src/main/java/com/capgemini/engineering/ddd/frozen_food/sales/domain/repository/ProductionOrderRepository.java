package com.capgemini.engineering.ddd.frozen_food.sales.domain.repository;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.ProductionOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductionOrderRepository extends MongoRepository<ProductionOrder, String> {

    public Optional<ProductionOrder> findByProductionOrderID(ProductionOrderID productionOrderID);
}
