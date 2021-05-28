package com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.RouteID;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.Route;

public interface RouteRepo extends Repository<Route, RouteID> {

}
