package com.capgemini.engineering.ddd.frozen_food.domain.stock.repository;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderSupplierID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.entity.OrderSupplier;

import java.util.List;

public class OrdersSuppliersImpl implements OrdersSuppliers {
    @Override
    public List<OrderSupplier> all() {
        // TODO
        return null;
    }

    @Override
    public OrderSupplier withId(OrderSupplierID id) {
        // TODO
        return null;
    }

    @Override
    public boolean existsWithId(OrderSupplierID id) {
        // TODO
        return false;
    }

    @Override
    public void registerNew(OrderSupplier aggregateRoot) {
        // TODO
    }

    @Override
    public void update(OrderSupplier aggregateRoot) {
        // TODO
    }

    @Override
    public boolean existsWithOrderReference(String orderReference) {
        // TODO
        return false;
    }
}
