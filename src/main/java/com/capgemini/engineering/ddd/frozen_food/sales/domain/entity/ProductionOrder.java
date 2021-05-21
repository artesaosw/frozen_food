package com.capgemini.engineering.ddd.frozen_food.sales.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "productionOrder_sales")
public class ProductionOrder implements AggregateRoot, Serializable {

    @Id
    private String id;

    @NotNull
    private ProductionOrderID productionOrderID = Identificator.newInstance(ProductionOrderID.class);

    private Map<Product, Integer> products = new HashMap<>();

    @NotNull
    private OrderDeliveryState orderDeliveryState;

    private LocalDate productionDate;

    public ProductionOrder() {

    }

    public ProductionOrderID getProductionOrderID() {
        return productionOrderID;
    }

    public void setProductionOrderID(ProductionOrderID productionOrderID) {
        this.productionOrderID = productionOrderID;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderDeliveryState getOrderDeliveryState() {
        return orderDeliveryState;
    }

    public void setOrderDeliveryState(OrderDeliveryState orderDeliveryState) {
        this.orderDeliveryState = orderDeliveryState;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    @Override
    public Identificator id() {
        return this.productionOrderID;
    }

    @Override
    public boolean isEqualsTo(Object other) {
        return AggregateRoot.super.isEqualsTo(other);
    }

    @Override
    public int hashcode() {
        return AggregateRoot.super.hashcode();
    }
}
