package com.capgemini.engineering.ddd.frozen_food.sales.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.OrderID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Document(collection = "order_sales")
public class Order implements AggregateRoot, Serializable {

    @Id
    private String id;

    private OrderID orderID;

    @NotEmpty
    private List<Product> productsOrdered = new ArrayList<>();

    @NotEmpty
    private List<Integer> quantitiesOrdered = new ArrayList<>();

    @NotNull
    private Customer orderedBy;

    private OrderStatus orderStatus;

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

    public List<Product> getProductsOrdered() {
        return productsOrdered;
    }

    public void setProductsOrdered(List<Product> productsOrdered) {
        this.productsOrdered = productsOrdered;
    }

    public List<Integer> getQuantitiesOrdered() {
        return quantitiesOrdered;
    }

    public void setQuantitiesOrdered(List<Integer> quantitiesOrdered) {
        this.quantitiesOrdered = quantitiesOrdered;
    }

    public Customer getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(Customer orderedBy) {
        this.orderedBy = orderedBy;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
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

    public boolean addProduct(Product product, Integer quantity) {

        if (!this.productsOrdered.contains(product)) {
            this.productsOrdered.add(product);
            this.quantitiesOrdered.add(quantity);

            return true;
        }
        return false;
    }

    public boolean validateOrderProducts() {

        if(this.getProductsOrdered().size() == 0 || this.getQuantitiesOrdered().size() == 0) {
            return false;
        }

        if(this.getProductsOrdered().size() != this.getQuantitiesOrdered().size()) {
            return false;
        }

        for (int i = 0; i < this.getProductsOrdered().size(); i++) {
            for(int j = i + 1; j < this.getProductsOrdered().size(); j++) {
                if (this.getProductsOrdered().get(i).equals(this.getProductsOrdered().get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public double computeTotalCost() {
        double totalCost = 0;

        for(int i = 0; i < this.productsOrdered.size(); i++) {
            totalCost += this.getQuantitiesOrdered().get(i) * this.productsOrdered.get(i).getUnitPrice();
        }

        return totalCost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderID=" + orderID +
                ", productsOrdered=" + productsOrdered +
                ", quantitiesOrdered=" + quantitiesOrdered +
                ", orderedBy=" + orderedBy +
                ", orderDeliveryState=" + orderStatus +
                ", creationDate=" + creationDate +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}
