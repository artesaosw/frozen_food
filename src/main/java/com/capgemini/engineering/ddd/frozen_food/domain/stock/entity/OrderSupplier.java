package com.capgemini.engineering.ddd.frozen_food.domain.stock.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderSupplierID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.OrderSupplierStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

@Getter
public class OrderSupplier implements AggregateRoot, Serializable {

    private OrderSupplierID id;

    private String orderReference;

    private Map<Requirement, Integer> orders;

    private SupplierID supplierID;

    private LocalDate purchaseDate;

    private Integer purchaseValue;

    @Setter
    private OrderSupplierStatus orderSupplierStatus;

    protected OrderSupplier() {
    }

    public OrderSupplier(@NotEmpty String orderReference, @NotEmpty Map<Requirement, Integer> orders, @NotNull SupplierID supplierID, @NotNull Integer purchaseValue) {
        this.orderReference = orderReference;
        this.id = Identificator.newInstance(OrderSupplierID.class);
        this.orders = orders;
        this.supplierID = supplierID;
        this.purchaseDate = LocalDate.now();
        this.purchaseValue = purchaseValue;
        this.orderSupplierStatus = OrderSupplierStatus.UNDELIVERED;
    }

    public OrderSupplierID id() {
        return this.id;
    }
}
