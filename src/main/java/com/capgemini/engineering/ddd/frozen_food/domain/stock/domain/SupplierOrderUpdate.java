package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierOrderID;

public class SupplierOrderUpdate extends DomainEvent {

    private SupplierOrderID supplierOrderID;

    public SupplierOrderUpdate(SupplierOrderID supplierOrderID) {
        super();
        this.supplierOrderID = supplierOrderID;
    }
}
