package com.capgemini.engineering.ddd.frozen_food.stock.domain.event;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.SupplierOrderID;

public class SupplierOrderRegistered extends DomainEvent {

    private SupplierOrderID supplierOrderID;

    public SupplierOrderRegistered(SupplierOrderID supplierOrderID) {
        super();
        this.supplierOrderID = supplierOrderID;
    }
}
