package com.capgemini.engineering.ddd.frozen_food._shared.dto.production_sales;

import com.capgemini.engineering.ddd.frozen_food._shared.ProductionOrderState;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Map;

public class ProductionOrderDTO {

    @NotNull
    private ProductionOrderID productionOrderID;

    @NotEmpty
    private Map<String, Integer> recipes;

    @NotNull
    private ProductionOrderState productionOrderState;

    private LocalDate productionDate;

    public ProductionOrderDTO() {

    }

    public ProductionOrderID getProductionOrderID() {
        return productionOrderID;
    }

    public void setProductionOrderID(ProductionOrderID productionOrderID) {
        this.productionOrderID = productionOrderID;
    }

    public Map<String, Integer> getRecipes() {
        return recipes;
    }

    public void setRecipes(Map<String, Integer> recipes) {
        this.recipes = recipes;
    }

    public ProductionOrderState getProductionOrderState() {
        return productionOrderState;
    }

    public void setProductionOrderState(ProductionOrderState productionOrderState) {
        this.productionOrderState = productionOrderState;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }
}
