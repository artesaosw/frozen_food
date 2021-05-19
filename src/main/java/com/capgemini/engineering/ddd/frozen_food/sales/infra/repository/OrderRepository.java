package com.capgemini.engineering.ddd.frozen_food.sales.infra.repository;

import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String>, Orders {


}
