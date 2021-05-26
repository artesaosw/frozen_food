package com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ProductionOrderIngredientDTO {

    private ProductionOrderID id;
    // String represents IngredientID
    private Map<String, Integer> orders;

    public ProductionOrderIngredientDTO() {
        this.orders = new HashMap<>();
    }
}
