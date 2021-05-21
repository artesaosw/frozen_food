package com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery;

import com.capgemini.engineering.ddd.frozen_food._shared.id.CustomerID;
import com.capgemini.engineering.ddd.frozen_food._shared.id.OrderID;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Map;

public class OrderDTO {

    @NotNull
    private OrderID orderID;

    @NotNull
    private Map<ProductDTO, Integer> productsOrdered;

    @NotNull
    private CustomerID orderedBy;

    @NotNull
    private OrderDeliveryState orderDeliveryState;

    //to be set by the Delivery context
    private LocalDate deliveryDate;

    public OrderDTO() {

    }

    public OrderDTO(OrderID orderID, Map<ProductDTO, Integer> productsOrdered, CustomerID orderedBy, OrderDeliveryState orderDeliveryState, LocalDate date) {
        this.orderID = orderID;
        this.productsOrdered = productsOrdered;
        this.orderedBy = orderedBy;
        this.orderDeliveryState = orderDeliveryState;
        this.deliveryDate = date;
    }

    public OrderID getOrderID() {
        return orderID;
    }

    public void setOrderID(OrderID orderID) {
        this.orderID = orderID;
    }

    public Map<ProductDTO, Integer> getProductsOrdered() {
        return productsOrdered;
    }

    public void setProductsOrdered(Map<ProductDTO, Integer> productsOrdered) {
        this.productsOrdered = productsOrdered;
    }

    public CustomerID getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(CustomerID orderedBy) {
        this.orderedBy = orderedBy;
    }

    public OrderDeliveryState getOrderDeliveryState() {
        return orderDeliveryState;
    }

    public void setOrderDeliveryState(OrderDeliveryState orderDeliveryState) {
        this.orderDeliveryState = orderDeliveryState;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


}
