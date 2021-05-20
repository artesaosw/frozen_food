package com.capgemini.engineering.ddd.frozen_food.stock.infra.dao;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ChefOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.repository.ChefOrders;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ChefOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChefOrderDAO extends MongoRepository<ChefOrder, ChefOrderID> {

    List<ChefOrder> findAllByOrderStatus(OrderStatus orderStatus);

    boolean existsByOrderReference(String orderReference);

    ChefOrder findByChefOrderID(ChefOrderID id);
}
