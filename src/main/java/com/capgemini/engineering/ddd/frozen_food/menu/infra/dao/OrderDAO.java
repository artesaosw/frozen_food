package com.capgemini.engineering.ddd.frozen_food.menu.infra.dao;

import com.capgemini.engineering.ddd.frozen_food._shared.OrderID;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderDAO extends MongoRepository<Order, OrderID> {

    boolean existsByOrderReference(String orderReference);
}
