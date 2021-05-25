package com.capgemini.engineering.ddd.frozen_food.delivery.infra.mongoDAO;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.Route;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository.RouteRepository;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.RouteID;

import java.util.List;

public class RouteDatabase implements RouteRepository {

    @Override
    public List<Route> all() {
        return null;
    }

    @Override
    public Route withId(RouteID id) {
        return null;
    }

    @Override
    public boolean existsWithId(RouteID id) {
        return false;
    }

    @Override
    public void registerNew(Route aggregateRoot) {

    }

    @Override
    public void update(Route aggregateRoot) {

    }
}
