package com.capgemini.engineering.ddd.frozen_food.stock.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.InvalidElementException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class ProductionOrder implements AggregateRoot, Serializable {

    @Id
    private ProductionOrderID id;

    private String orderReference;

    private Map<Ingredient, Integer> orders;

    private LocalDate orderDate;

    private OrderStatus orderStatus;

    public ProductionOrder (@NotEmpty String orderReference, @NotEmpty Map<Ingredient, Integer> orders) {
        this.orderReference = orderReference;
        this.id = Identificator.newInstance(ProductionOrderID.class);
        this.orders = new HashMap<>();
        this.orderDate = LocalDate.now();
        this.orderStatus = OrderStatus.UNDELIVERED;
    }

    @Override
    public ProductionOrderID id() {
        return null;
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
