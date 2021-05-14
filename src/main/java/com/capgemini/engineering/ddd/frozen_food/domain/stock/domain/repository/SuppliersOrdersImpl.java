package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.SupplierOrder;

import java.util.List;

public class SuppliersOrdersImpl implements SuppliersOrders {
    @Override
    public List<SupplierOrder> all() {
        // TODO
        return null;
    }

    @Override
    public SupplierOrder withId(SupplierOrderID id) {
        // TODO
        return null;
    }

    @Override
    public boolean existsWithId(SupplierOrderID id) {
        // TODO
        return false;
    }

    @Override
    public void registerNew(SupplierOrder aggregateRoot) {
        // TODO
    }

    @Override
    public void update(SupplierOrder aggregateRoot) {
        // TODO
    }

    @Override
    public boolean existsWithOrderReference(String orderReference) {
        // TODO
        return false;
    }
}
