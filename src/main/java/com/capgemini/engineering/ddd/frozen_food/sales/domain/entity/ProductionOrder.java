package com.capgemini.engineering.ddd.frozen_food.sales.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.ProductionOrderState;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "productionOrder_sales")
public class ProductionOrder implements AggregateRoot, Serializable {

    @Id
    private String id;

    private ProductionOrderID productionOrderID;

    @NotEmpty
    private List<Product> products;

    @NotEmpty
    private List<Integer> quantities;

    private ProductionOrderState productionOrderState;

    private LocalDate productionDate;

    public ProductionOrder() {

    }

    public ProductionOrderID getProductionOrderID() {
        return productionOrderID;
    }

    public void setProductionOrderID(ProductionOrderID productionOrderID) {
        this.productionOrderID = productionOrderID;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Integer> quantities) {
        this.quantities = quantities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductionOrderState getProductionOrderState() {
        return productionOrderState;
    }

    public void setProductionOrderState (ProductionOrderState productionOrderState) {
        this.productionOrderState = productionOrderState;
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

    /*
    Check if List<Product> does not contain duplicates and has the same size as List<Integer>.
    Used to validate if order is well-formed.
     */
    public boolean validateOrder() {

        if(this.products.size() == 0 || this.quantities.size() == 0) {
            return false;
        }

        if(this.products.size() != this.quantities.size()) {
            return false;
        }

        for (int i = 0; i < this.products.size(); i++) {
            for(int j = i + 1; j < this.products.size(); j++) {
                if (this.products.get(i).equals(this.products.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
