package com.capgemini.engineering.ddd.frozen_food.stock.infra.dao;

import com.capgemini.engineering.ddd.frozen_food._shared.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ChefOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ChefOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChefOrderDAO extends MongoRepository<ChefOrder, ChefOrderID> {

    boolean existsByOrderReference(String orderReference);

    List<ChefOrder> findAllByOrderStatus(OrderStatus orderStatus);
}
