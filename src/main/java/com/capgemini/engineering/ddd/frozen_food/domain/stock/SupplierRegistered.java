package com.capgemini.engineering.ddd.frozen_food.domain.stock;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierID;

public class SupplierRegistered extends DomainEvent {

    private SupplierID supplierID;

    public SupplierRegistered(SupplierID supplierID) {
        super();
        this.supplierID = supplierID;
    }
}
