package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String>, Orders {

}
