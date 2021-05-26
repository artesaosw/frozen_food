package com.capgemini.engineering.ddd.frozen_food.domain.producao.repository;


import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.ProductionOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductionOrders extends MongoRepository<ProductionOrder, ProductionOrderID> {
}
