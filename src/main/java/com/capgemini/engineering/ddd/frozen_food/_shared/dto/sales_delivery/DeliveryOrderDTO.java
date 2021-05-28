package com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery;

import com.capgemini.engineering.ddd.frozen_food._shared.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food._shared.id.OrderID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class DeliveryOrderDTO {

    @NotNull
    private OrderID orderID;

    @NotEmpty
    private List<ProductDTO> products;

    @NotNull
    private CustomerDTO orderedBy;

    @NotNull
    private OrderStatus orderStatus;

    //to be set by the Delivery context
    private LocalDate deliveryDate;

    public DeliveryOrderDTO() {

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

    public CustomerDTO getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(CustomerDTO orderedBy) {
        this.orderedBy = orderedBy;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

}
