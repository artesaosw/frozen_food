package com.capgemini.engineering.ddd.frozen_food.stock.infra.event;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.SupplierID;

public class SupplierRegistered extends DomainEvent {

    private SupplierID supplierID;

    public SupplierRegistered(SupplierID supplierID) {
        super();
        this.supplierID = supplierID;
    }
}
