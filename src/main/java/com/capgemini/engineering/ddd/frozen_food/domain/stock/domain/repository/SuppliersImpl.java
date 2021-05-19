package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food._shared.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.NIF;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.Supplier;

import java.util.List;

public class SuppliersImpl implements Suppliers {
    @Override
    public List<Supplier> all() {
        // TODO
        return null;
    }

    @Override
    public Supplier withId(SupplierID id) {
        // TODO
        return null;
    }

    @Override
    public boolean existsWithId(SupplierID id) {
        // TODO
        return false;
    }

    @Override
    public void registerNew(Supplier aggregateRoot) {
        // TODO
    }

    @Override
    public void update(Supplier aggregateRoot) {
        // TODO
    }

    @Override
    public boolean existsWithNIF(NIF nif) {
        // TODO
        return false;
    }
}
