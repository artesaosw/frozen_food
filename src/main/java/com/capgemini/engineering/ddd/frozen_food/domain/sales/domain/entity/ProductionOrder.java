package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductionOrderID;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ProductionOrder implements AggregateRoot, Serializable {

    @Id
    private String id;

    private ProductionOrderID productionOrderID = Identificator.newInstance(ProductionOrderID.class);

    private Map<Product, Integer> itemsOrdered = new HashMap<>();

    public ProductionOrder() {

    }

    public ProductionOrderID getProductionOrderID() {
        return productionOrderID;
    }

    public void setProductionOrderID(ProductionOrderID productionOrderID) {
        this.productionOrderID = productionOrderID;
    }

    public Map<Product, Integer> getItemsOrdered() {
        return itemsOrdered;
    }

    public void setItemsOrdered(Map<Product, Integer> itemsOrdered) {
        this.itemsOrdered = itemsOrdered;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
