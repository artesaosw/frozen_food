package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Events;
import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ChefOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.SupplierRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.NIF;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.Supplier;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.SupplierOrder;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository.Suppliers;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository.SuppliersImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SuppliersService implements DomainServices {

    private Suppliers suppliers() {
        return Domain.suppliers();
    }

    public Supplier getSupplierById(@NotNull SupplierID id) {
        Supplier supplier = null;
        // TODO
        return supplier;
    }

    public Supplier getSupplierByName(@NotBlank String name) {
        Supplier supplier = null;
        // TODO
        return supplier;
    }

    public List<Supplier> getAllSuppliers() {
        return suppliers().all();
    }

    public void registerNewSupplier(@NotBlank String name, @NotNull NIF nif) {
        if (suppliers().existsWithNIF(nif)) {
            throw new IllegalArgumentException("Already exists another supplier with the same NIF.");
        }
        Supplier supplier = new Supplier(name, nif);
        suppliers().registerNew(supplier);
        Events.report(new SupplierRegistered(supplier.id()));
    }

    public void registerNewSupplier(@NotNull Supplier supplier) {
        if (suppliers().existsWithNIF(supplier.getNif())) {
            throw new IllegalArgumentException("Already exists another supplier with the same NIF.");
        }
        suppliers().registerNew(supplier);
        Events.report(new SupplierRegistered(supplier.id()));
    }

    public void updateSupplier(@NotNull Supplier supplier) {
        if (!suppliers().existsWithId(supplier.getId())) {
            throw new IllegalArgumentException("There is no supplier with id = " + supplier.getId().toString());
        }
        suppliers().update(supplier);
    }

    public void deleteSupplier(@NotNull SupplierID id) {
        if (!suppliers().existsWithId(id)) {
            throw new IllegalArgumentException("There is no supplier with id = " + id.toString());
        }
        suppliers().delete(id);
    }
}
