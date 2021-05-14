package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierOrderID;

public class SupplierOrderRegistered extends DomainEvent {

    private SupplierOrderID supplierOrderID;

    public SupplierOrderRegistered(SupplierOrderID supplierOrderID) {
        super();
        this.supplierOrderID = supplierOrderID;
    }
}
