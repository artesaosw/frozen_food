package com.capgemini.engineering.ddd.frozen_food.stock.infra.dao;

import com.capgemini.engineering.ddd.frozen_food._shared.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ProductionOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductionOrderDAO extends MongoRepository<ProductionOrder, ProductionOrderID> {

    List<ProductionOrder> findAllByOrderStatus(OrderStatus orderStatus);

    boolean existsByOrderReference(String orderReference);
}
