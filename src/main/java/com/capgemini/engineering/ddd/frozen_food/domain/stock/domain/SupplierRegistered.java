package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.SupplierID;

public class SupplierRegistered extends DomainEvent {

    private SupplierID supplierID;

    public SupplierRegistered(SupplierID supplierID) {
        super();
        this.supplierID = supplierID;
    }
}
