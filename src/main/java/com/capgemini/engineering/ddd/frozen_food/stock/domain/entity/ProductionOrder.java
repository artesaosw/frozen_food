package com.capgemini.engineering.ddd.frozen_food.stock.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.InvalidElementException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.OrderStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@Document(collection = "production_order_stock")
public class ProductionOrder implements AggregateRoot, Serializable {

    @Id
    private ProductionOrderID id;

    @Setter
    private String orderReference;

    @Setter
    private Map<Ingredient, Integer> orders;

    @Setter
    private LocalDate orderDate;

    @Setter
    private OrderStatus orderStatus;

    @JsonCreator
    public ProductionOrder (@NotEmpty String orderReference, @NotEmpty Map<Ingredient, Integer> orders) {
        this.id = Identificator.newInstance(ProductionOrderID.class);
        this.orderReference = orderReference;
        this.orders = new HashMap<>(orders);
        this.orderDate = LocalDate.now();
        this.orderStatus = OrderStatus.UNDELIVERED;
    }

    public ProductionOrder (@NotNull ProductionOrderID id, @NotEmpty String orderReference, @NotEmpty Map<Ingredient, Integer> orders) {
        this.id = Identificator.clone(id);
        this.orderReference = orderReference;
        this.orders = new HashMap<>(orders);
        this.orderDate = LocalDate.now();
        this.orderStatus = OrderStatus.UNDELIVERED;
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

    public void addIngredientToOrder(Ingredient ingredient, @Positive Integer quantity) {
        if (this.orders.containsKey(ingredient)) {
            throw new InvalidElementException("The ingredient already exists in the order!");
        } else {
            this.orders.put(ingredient, quantity);
        }
    }

    public void removeIngredientFromOrder(Ingredient ingredient) {
        this.orders.remove(ingredient);
    }

    public void updateIngredientQuantityInOrder(Ingredient ingredient, @NotNull Integer quantity) {
        if (!this.orders.containsKey(ingredient)) {
            throw new InvalidElementException("The ingredient does not exists in the order!");
        } else {
            this.orders.put(ingredient, this.orders.get(ingredient) + quantity);
        }
    }
}
