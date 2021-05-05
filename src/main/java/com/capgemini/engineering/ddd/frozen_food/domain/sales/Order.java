package com.capgemini.engineering.ddd.frozen_food.domain.sales;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.Recipe;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// #1: sera que deve incluir preço total?
public class Order implements AggregateRoot, Serializable {

    private OrderID id;

    // lista de receitas do menu pedidas
    // problema1: vai incluir as receitas "intermedias" (ex.: cebola refogada)
    private Map<Recipe, Integer> itemsOrdered = new HashMap<>();

    private Customer orderedBy;

    public Order(@NotNull Customer orderedBy) {
        this.id = Identificator.newInstance(OrderID.class);
        this.orderedBy = orderedBy;
    }

    @Override
    public Identificator id() {
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

    public void addItemToOrder(Recipe item, int quantity) {
        //check for null quantities
        if(quantity <= 0) {
            return;
        }

        //if item is not in the list, add it
        if(!this.itemsOrdered.containsKey(item)) {
            this.itemsOrdered.put(item, quantity);
        }
        else {
            //increment what's already there
            int newQuantity = this.itemsOrdered.get(item) + quantity;
            this.itemsOrdered.put(item, newQuantity);
        }
    }

    public void removeItemFromOrder(Recipe item) {
        this.itemsOrdered.remove(item);
    }

    public void decrementItemInOrder(Recipe item, int quantity) {
        //check if item exists and if available amount is >= than quantity
        if(this.itemsOrdered.containsKey(item) && this.itemsOrdered.get(item) >= 0) {
            int newQuantity = this.itemsOrdered.get(item) - quantity;
            this.itemsOrdered.put(item, newQuantity);
        }
    }
}
