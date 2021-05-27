package com.capgemini.engineering.ddd.frozen_food.stock.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food._shared.utils.MapIngredientIDSerializer;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.SupplierOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.InvalidElementException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
@Validated
@Document(collection = "supplier_order_stock")
public class SupplierOrder implements AggregateRoot, Serializable {

    @Id
    private SupplierOrderID id;

    @Setter
    private String orderReference;

    @Setter
    @JsonSerialize(using = MapIngredientIDSerializer.class)
    private Map<String, Integer> orders;

    @Setter
    private SupplierID supplierID;

    @Setter
    private LocalDate purchaseDate;

    @Setter
    private double purchaseValue;

    @Setter
    private OrderStatus orderStatus;

    @JsonCreator
    public SupplierOrder(@NotEmpty String orderReference, @NotEmpty Map<String, Integer> orders, @NotNull SupplierID supplierID, @NotNull double purchaseValue) {
        this.id = Identificator.newInstance(SupplierOrderID.class);
        this.orderReference = orderReference;
        this.orders = new HashMap<>(orders);
        this.supplierID = supplierID;
        this.purchaseDate = LocalDate.now();
        this.purchaseValue = purchaseValue;
        this.orderStatus = OrderStatus.UNDELIVERED;
    }

    public SupplierOrder(@NotNull SupplierOrderID id, @NotEmpty String orderReference, @NotEmpty Map<String, Integer> orders, @NotNull SupplierID supplierID, @NotNull double purchaseValue) {
        this.id = Identificator.clone(id);
        this.orderReference = orderReference;
        this.orders = new HashMap<>(orders);
        this.supplierID = supplierID;
        this.purchaseDate = LocalDate.now();
        this.purchaseValue = purchaseValue;
        this.orderStatus = OrderStatus.UNDELIVERED;
    }

    public SupplierOrderID id() {
        return this.id;
    }

    @Override
    public boolean isEqualsTo(Object other) {
        return AggregateRoot.super.isEqualsTo(other);
    }

    @Override
    public int hashcode() {
        return AggregateRoot.super.hashcode();
    }

    public void addIngredientToOrder(String ingredientID, @Positive Integer quantity) {
        if (this.orders.containsKey(ingredientID)) {
            throw new InvalidElementException("The ingredient already exists in the order!");
        } else {
            this.orders.put(ingredientID, quantity);
        }
    }

    public void removeIngredientFromOrder(IngredientID ingredientID) {
        this.orders.remove(ingredientID);
    }

    public void updateIngredientQuantityInOrder(String ingredientID, @NotNull Integer quantity) {
        if (!this.orders.containsKey(ingredientID)) {
            throw new InvalidElementException("The ingredient does not exists in the order!");
        } else {
            this.orders.put(ingredientID, this.orders.get(ingredientID) + quantity);
        }
    }
}
