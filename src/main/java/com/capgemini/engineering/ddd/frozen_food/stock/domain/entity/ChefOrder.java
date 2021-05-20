package com.capgemini.engineering.ddd.frozen_food.stock.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ChefOrderID;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.InvalidElementException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@Document(collection = "chef_order_stock")
public class ChefOrder implements AggregateRoot, Serializable {

    @Id
    private String id;

    private ChefOrderID chefOrderID;

    private String orderReference;

    private Map<Ingredient, Integer> orders;

    private LocalDate orderDate;

    private OrderStatus orderStatus;

    public ChefOrder (@NotEmpty String orderReference, @NotEmpty Map<Ingredient, Integer> orders) {
        this.orderReference = orderReference;
        this.chefOrderID = Identificator.newInstance(ProductionOrderID.class);
        this.orders = new HashMap<>(orders);
        this.orderDate = LocalDate.now();
        this.orderStatus = OrderStatus.UNDELIVERED;
    }

    @Override
    public ChefOrderID id() {
        return chefOrderID;
    }

    @Override
    public boolean isEqualsTo(Object other) {
        return AggregateRoot.super.isEqualsTo(other);
    }

    @Override
    public int hashcode() {
        return AggregateRoot.super.hashcode();
    }

    public void addIngredientToOrder(@NotNull Ingredient ingredient, @Positive Integer quantity) {
        if (this.orders.containsKey(ingredient)) {
            throw new InvalidElementException("The ingredient already exists in the order!");
        } else {
            this.orders.put(ingredient, quantity);
        }
    }

    public void removeIngredientFromOrder(@NotNull Ingredient ingredient) {
        this.orders.remove(ingredient);
    }

    public void updateIngredientQuantityInOrder(@NotNull Ingredient ingredient, @NotNull Integer quantity) {
        if (!this.orders.containsKey(ingredient)) {
            throw new InvalidElementException("The ingredient does not exists in the order!");
        } else {
            this.orders.put(ingredient, this.orders.get(ingredient) + quantity);
        }
    }
}
