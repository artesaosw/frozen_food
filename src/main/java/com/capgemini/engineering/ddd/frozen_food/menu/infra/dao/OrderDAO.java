package com.capgemini.engineering.ddd.frozen_food.menu.infra.dao;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ChefOrderID;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderDAO extends MongoRepository<Order, ChefOrderID> {

    boolean existsByOrderReference(String orderReference);
}
