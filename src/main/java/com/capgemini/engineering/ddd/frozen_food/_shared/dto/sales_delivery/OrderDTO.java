package com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery;

import com.capgemini.engineering.ddd.frozen_food._shared.OrderDeliveryState;
import com.capgemini.engineering.ddd.frozen_food._shared.id.CustomerID;
import com.capgemini.engineering.ddd.frozen_food._shared.id.OrderID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class OrderDTO {

    @NotNull
    private OrderID orderID;

    @NotEmpty
    private List<ProductDTO> products;

    @NotEmpty
    private List<Integer> quantities;

    @NotNull
    private CustomerID orderedBy;

    @NotNull
    private OrderDeliveryState orderDeliveryState;

    //to be set by the Delivery context
    private LocalDate deliveryDate;

    public OrderDTO() {

    }

    public OrderID getOrderID() {
        return orderID;
    }

    public void setOrderID(OrderID orderID) {
        this.orderID = orderID;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Integer> quantities) {
        this.quantities = quantities;
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
