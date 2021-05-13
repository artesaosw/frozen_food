package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderID;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// #1: sera que deve incluir preço total?
public class Order implements AggregateRoot, Serializable {

    @Id
    private String id;

    @NotNull
    private OrderID orderID = Identificator.newInstance(OrderID.class);

    // lista de receitas do menu pedidas
    // problema1: vai incluir as receitas "intermedias" (ex.: cebola refogada)
    private Map<Product, Integer> itemsOrdered = new HashMap<>();

    @NotNull
    private Customer orderedBy;

    @NotNull
    private OrderState orderState = OrderState.PROCESSING;

    //we compute the total cost of the order separately, whenever we need it. Safer this way.
    //private Double totalCost;

    public Order() {

    }

    public Order(@NotNull Customer orderedBy) {
        this.orderedBy = orderedBy;
        this.orderState = OrderState.PROCESSING;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderID getOrderID() {
        return orderID;
    }

    public void setOrderID(OrderID orderID) {
        this.orderID = orderID;
    }

    @Override
    public Identificator id() {
        return this.orderID;
    }

    public Map<Product, Integer> getItemsOrdered() {
        return itemsOrdered;
    }

    public void setItemsOrdered(Map<Product, Integer> itemsOrdered) {
        this.itemsOrdered = itemsOrdered;
    }

    public Customer getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(Customer orderedBy) {
        this.orderedBy = orderedBy;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

//    public Double getTotalCost() {
//        return totalCost;
//    }
//
//    public void setTotalCost(Double totalCost) {
//        this.totalCost = totalCost;
//    }

    @Override
    public boolean isEqualsTo(Object other) {
        return AggregateRoot.super.isEqualsTo(other);
    }

    @Override
    public int hashcode() {
        return AggregateRoot.super.hashcode();
    }

    public void addItemToOrder(Product item, int quantity) {
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

    public void removeItemFromOrder(Product item) {
        this.itemsOrdered.remove(item);
    }

    public void decrementItemInOrder(Product item, int quantity) {

        //check if item exists and if available amount is >= than quantity
        if(this.itemsOrdered.containsKey(item) && this.itemsOrdered.get(item) >= 0) {
            int newQuantity = this.itemsOrdered.get(item) - quantity;
            this.itemsOrdered.put(item, newQuantity);
        }
    }

    public double computeTotalCost() {
        double totalCost = 0;

        for (Product product : this.itemsOrdered.keySet()) {
            totalCost += product.getUnitPrice() * this.itemsOrdered.get(product);
        }

        return totalCost;
    }
}
