package com.capgemini.engineering.ddd.frozen_food.domain.stock.repository;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.entity.Order;

import java.util.List;

public class OrdersImpl implements Orders {
    @Override
    public List<Order> all() {
        // TODO
        return null;
    }

    @Override
    public Order withId(OrderID id) {
        // TODO
        return null;
    }

    @Override
    public boolean existsWithId(OrderID id) {
        // TODO
        return false;
    }

    @Override
    public void registerNew(Order aggregateRoot) {
        // TODO
    }

    @Override
    public void update(Order aggregateRoot) {
        // TODO
    }

    @Override
    public boolean existsWithOrderReference(String orderReference) {
        // TODO
        return false;
    }
}
