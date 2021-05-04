package com.capgemini.engineering.ddd.frozen_food.domain.stock.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

@Getter
public class Order implements AggregateRoot, Serializable {

    private OrderID id;

    private String orderReference;

    private Map<Ingredient, Integer> orders;

    private SupplierID supplierID;

    private LocalDate purchaseDate;

    private Integer purchaseValue;

    @Setter
    private OrderStatus orderStatus;

    protected Order() {
    }

    public Order(@NotEmpty String orderReference, @NotEmpty Map<Ingredient, Integer> orders, @NotNull SupplierID supplierID, @NotNull Integer purchaseValue) {
        this.orderReference = orderReference;
        this.id = Identificator.newInstance(OrderID.class);
        this.orders = orders;
        this.supplierID = supplierID;
        this.purchaseDate = LocalDate.now();
        this.purchaseValue = purchaseValue;
        this.orderStatus = OrderStatus.UNDELIVERED;
    }

    public OrderID id() {
        return this.id;
    }
}
