package com.capgemini.engineering.ddd.frozen_food.sales.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.OrderDeliveryState;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.OrderID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "order_sales")
public class Order implements AggregateRoot, Serializable {

    @Id
    private String id;

    private OrderID orderID;

    @NotEmpty
    private Map<Product, Integer> productsOrdered = new HashMap<>();

    @NotNull
    private Customer orderedBy;

    private OrderDeliveryState orderDeliveryState;

    //a class in the Sales context containing business logic is responsible for initializing the date for an Order object
    private LocalDate creationDate;

    //defined by Delivery context
    private LocalDate deliveryDate;

    public Order() {

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

    public Map<Product, Integer> getProductsOrdered() {
        return productsOrdered;
    }

    public void setProductsOrdered(Map<Product, Integer> productsOrdered) {
        this.productsOrdered = productsOrdered;
    }

    public Customer getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(Customer orderedBy) {
        this.orderedBy = orderedBy;
    }

    public OrderDeliveryState getOrderDeliveryState() {
        return orderDeliveryState;
    }

    public void setOrderDeliveryState(OrderDeliveryState orderDeliveryState) {
        this.orderDeliveryState = orderDeliveryState;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

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
        if(!this.productsOrdered.containsKey(item)) {
            this.productsOrdered.put(item, quantity);
        }
        else {
            //increment what's already there
            int newQuantity = this.productsOrdered.get(item) + quantity;
            this.productsOrdered.put(item, newQuantity);
        }
    }

    public void removeItemFromOrder(Product item) {
        this.productsOrdered.remove(item);
    }

    public void decrementItemInOrder(Product item, int quantity) {

        //check if item exists and if available amount is >= than quantity
        if(this.productsOrdered.containsKey(item) && this.productsOrdered.get(item) >= 0) {
            int newQuantity = this.productsOrdered.get(item) - quantity;
            this.productsOrdered.put(item, newQuantity);
        }
    }

    public double computeTotalCost() {
        double totalCost = 0;

        for (Product product : this.productsOrdered.keySet()) {
            totalCost += product.getUnitPrice() * this.productsOrdered.get(product);
        }

        return totalCost;
    }
}
