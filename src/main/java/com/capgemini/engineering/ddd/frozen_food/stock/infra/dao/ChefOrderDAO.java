package com.capgemini.engineering.ddd.frozen_food.stock.infra.dao;

import com.capgemini.engineering.ddd.frozen_food._shared.ChefOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ChefOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChefOrderDAO extends MongoRepository<ChefOrder, ChefOrderID> {

    List<ChefOrder> findAllByOrderStatus(OrderStatus orderStatus);

    boolean existsByOrderReference(String orderReference);
}
