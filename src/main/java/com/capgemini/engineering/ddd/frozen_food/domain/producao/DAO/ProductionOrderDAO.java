package com.capgemini.engineering.ddd.frozen_food.domain.producao.DAO;


import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.ProductionOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductionOrderDAO extends MongoRepository<ProductionOrder, ProductionOrderID> {
}
