package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.dao.SupplierDAO;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.NIF;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SuppliersImpl implements Suppliers {

    @Autowired
    SupplierDAO supplierDAO;

    @Override
    public List<Supplier> all() {
        return supplierDAO.getSuppliers();
    }

    @Override
    public Supplier withId(SupplierID id) {
        return supplierDAO.getSupplierById(id);
    }

    @Override
    public boolean existsWithId(SupplierID id) {
        // TODO
        return false;
    }

    @Override
    public void registerNew(Supplier aggregateRoot) {
        supplierDAO.addSupplier(aggregateRoot);
    }

    @Override
    public void update(Supplier aggregateRoot) {
        supplierDAO.updateSupplier(aggregateRoot);
    }

    @Override
    public boolean existsWithNIF(NIF nif) {
        // TODO
        return false;
    }

    @Override
    public boolean delete(SupplierID id) {
        return supplierDAO.deleteSupplier(withId(id));
    }
}
