package com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ProductionOrderDTO {

    private ProductionOrderID id;
    private String orderReference;
    // String represents IngredientID
    private Map<String, Integer> orders;

    public ProductionOrderDTO() {
        this.orders = new HashMap<>();
    }
}
