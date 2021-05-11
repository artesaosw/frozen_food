package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.ProductionOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductionOrderRepository extends MongoRepository<ProductionOrder, String>, ProductionOrders {
}
