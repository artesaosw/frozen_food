package com.capgemini.engineering.ddd.frozen_food.stock.infra.event;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.SupplierOrderID;

public class SupplierOrderUpdated extends DomainEvent {

    private SupplierOrderID supplierOrderID;

    public SupplierOrderUpdated(SupplierOrderID supplierOrderID) {
        super();
        this.supplierOrderID = supplierOrderID;
    }
}
