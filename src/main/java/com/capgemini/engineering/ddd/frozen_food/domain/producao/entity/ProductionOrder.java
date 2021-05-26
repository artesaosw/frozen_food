package com.capgemini.engineering.ddd.frozen_food.domain.producao.entity;


import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductionOrderState;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.Recipe;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.valueObject.Dimensions;
import org.springframework.data.annotation.Id;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ProductionOrder implements Serializable, AggregateRoot {

    @NotNull @Id
    private ProductionOrderID id;

    @NotNull
    private Map<Recipe, Integer> products;

    @NotNull
    private ProductionOrderState productionOrderState;

    private LocalDate productionDate;

    private Dimensions dimensions;

    public ProductionOrder() {
        this.id = Identificator.newInstance(ProductionOrderID.class);
        this.products = new HashMap<>();
    }

    public ProductionOrderID getProductionOrderID() {
        return id;
    }

    public void setProductionOrderID(ProductionOrderID productionOrderID) {
        this.id = productionOrderID;
    }

    public Map<Recipe, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Recipe, Integer> products) {
        this.products = products;
    }

    public ProductionOrderState productionOrderState() {
        return productionOrderState;
    }

    public void setproductionOrderState(ProductionOrderState productionOrderState) {
        this.productionOrderState = productionOrderState;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public Dimensions getDimensions(){ return dimensions; }

    public void setDimensions(Dimensions dimensions){ this.dimensions = dimensions; }

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
}
