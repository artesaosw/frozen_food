package com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.IngredientDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ProductionOrderDTO {

    private ProductionOrderID id;
    private String orderReference;
    private Map<IngredientDTO, Integer> orders;

    public ProductionOrderDTO() {
        this.orders = new HashMap<>();
    }
}
