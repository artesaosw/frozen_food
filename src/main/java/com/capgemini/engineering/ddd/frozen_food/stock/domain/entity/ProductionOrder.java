package com.capgemini.engineering.ddd.frozen_food.stock.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food._shared.utils.MapIngredientIDSerializer;
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
@Document(collection = "production_order_stock")
public class ProductionOrder implements AggregateRoot, Serializable {

    @Id
    private ProductionOrderID id;

    @Setter
    private String orderReference;

    @Setter
    @JsonSerialize(using = MapIngredientIDSerializer.class)
    private Map<String, Integer> orders;

    @Setter
    private LocalDate orderDate;

    @Setter
    private OrderStatus orderStatus;

    @JsonCreator
    public ProductionOrder(@NotEmpty String orderReference, @NotEmpty Map<String, Integer> orders) {
        this.id = Identificator.newInstance(ProductionOrderID.class);
        this.orderReference = orderReference;
        this.orders = new HashMap<>(orders);
        this.orderDate = LocalDate.now();
        this.orderStatus = OrderStatus.ORDERED;
    }

    public ProductionOrder(@NotNull ProductionOrderID id, @NotEmpty String orderReference, @NotEmpty Map<String, Integer> orders) {
        this.id = id;
        this.orderReference = orderReference;
        this.orders = new HashMap<>(orders);
        this.orderDate = LocalDate.now();
        this.orderStatus = OrderStatus.ORDERED;
    }

    public ProductionOrder(@NotNull ProductionOrderID id, @NotNull OrderStatus orderStatus) {
        this.id = id;
        this.orderStatus = orderStatus;
    }

    public ProductionOrder(@NotNull ProductionOrderID id, @NotEmpty Map<String, Integer> orders) {
        this.id = id;
        this.orders = new HashMap<>(orders);
    }

    @Override
    public ProductionOrderID id() {
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
