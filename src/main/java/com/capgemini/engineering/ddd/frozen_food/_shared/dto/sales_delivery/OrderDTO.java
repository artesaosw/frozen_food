package com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery;

import com.capgemini.engineering.ddd.frozen_food._shared.id.CustomerID;
import com.capgemini.engineering.ddd.frozen_food._shared.id.OrderID;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductID;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Map;

public class OrderDTO {

    @NotNull
    private OrderID orderID;

    @NotNull
    private Map<ProductID, Integer> productsOrdered;

    @NotNull
    private CustomerID orderedBy;

    @NotNull
    private OrderState orderState;

    @NotNull
    private LocalDate date;

    public OrderDTO() {

    }

    public OrderDTO(OrderID orderID, Map<ProductID, Integer> productsOrdered, CustomerID orderedBy, OrderState orderState, LocalDate date) {
        this.orderID = orderID;
        this.productsOrdered = productsOrdered;
        this.orderedBy = orderedBy;
        this.orderState = orderState;
        this.date = date;
    }

    public OrderID getOrderID() {
        return orderID;
    }

    public void setOrderID(OrderID orderID) {
        this.orderID = orderID;
    }

    public Map<ProductID, Integer> getProductsOrdered() {
        return productsOrdered;
    }

    public void setProductsOrdered(Map<ProductID, Integer> productsOrdered) {
        this.productsOrdered = productsOrdered;
    }

    public CustomerID getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(CustomerID orderedBy) {
        this.orderedBy = orderedBy;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
