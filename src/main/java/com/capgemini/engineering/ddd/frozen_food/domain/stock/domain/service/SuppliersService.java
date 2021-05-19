package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.domain.Events;
import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.SupplierRegistered;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.NIF;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.Supplier;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository.Suppliers;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SuppliersService implements DomainServices {

    private Suppliers suppliers() {
        return Domain.suppliers();
    }

    public void registerNewSupplier(@NotBlank String name, @NotNull NIF nif) {
        if (suppliers().existsWithNIF(nif)) {
            throw new IllegalArgumentException("Already exists another supplier with the same NIF.");
        }
        Supplier supplier = new Supplier(name, nif);
        suppliers().registerNew(supplier);
        Events.report(new SupplierRegistered(supplier.id()));
    }
}
