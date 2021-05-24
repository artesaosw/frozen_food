package com.capgemini.engineering.ddd.frozen_food._shared.dto.production_sales;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class ProductionOrderDTO {

    @NotNull
    private ProductionOrderID productionOrderID;

    @NotNull
    private Map<RecipeDTO, Integer> products;

    public ProductionOrderDTO() {

    }

    public ProductionOrderDTO(ProductionOrderID productionOrderID, Map<RecipeDTO, Integer> products) {
        this.productionOrderID = productionOrderID;
        this.products = products;
    }

    public ProductionOrderID getProductionOrderID() {
        return productionOrderID;
    }

    public void setProductionOrderID(ProductionOrderID productionOrderID) {
        this.productionOrderID = productionOrderID;
    }

    public Map<RecipeDTO, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<RecipeDTO, Integer> products) {
        this.products = products;
    }

}
