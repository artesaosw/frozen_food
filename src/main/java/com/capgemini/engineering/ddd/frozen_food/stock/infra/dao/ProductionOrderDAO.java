package com.capgemini.engineering.ddd.frozen_food.stock.infra.dao;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ProductionOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductionOrderDAO extends MongoRepository<ProductionOrder, ProductionOrderID> {

    List<ProductionOrder> findAllByOrderStatus(OrderStatus orderStatus);

    boolean existsByOrderReference(String orderReference);

    ProductionOrder findByProductionOrderID(ProductionOrderID id);
}
