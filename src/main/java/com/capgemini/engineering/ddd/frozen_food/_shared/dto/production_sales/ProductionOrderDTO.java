package com.capgemini.engineering.ddd.frozen_food._shared.dto.production_sales;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductID;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class ProductionOrderDTO {

    @NotNull
    private ProductionOrderID productionOrderID;

    @NotNull
    private Map<ProductID, Integer> products;

    public ProductionOrderDTO() {

    }

    public ProductionOrderDTO(ProductionOrderID productionOrderID, Map<ProductID, Integer> products) {
        this.productionOrderID = productionOrderID;
        this.products = products;
    }

    public ProductionOrderID getProductionOrderID() {
        return productionOrderID;
    }

    public void setProductionOrderID(ProductionOrderID productionOrderID) {
        this.productionOrderID = productionOrderID;
    }

    public Map<ProductID, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<ProductID, Integer> products) {
        this.products = products;
    }
}
