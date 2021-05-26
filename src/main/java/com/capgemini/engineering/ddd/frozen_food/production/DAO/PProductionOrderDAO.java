package com.capgemini.engineering.ddd.frozen_food.production.DAO;


import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.production.entity.ProductionOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PProductionOrderDAO extends MongoRepository<ProductionOrder, ProductionOrderID> {
}
