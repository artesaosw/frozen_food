package com.capgemini.engineering.ddd.frozen_food.delivery.infra.mongoDAO;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.Route;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.RouteID;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RouteRepository extends MongoRepository<Route, RouteID> {

}

