package com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.IngredientDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ProductionOrderIngredientDTO {

    ProductionOrderID id;
    private Map<IngredientDTO, Integer> orders;

    public ProductionOrderIngredientDTO() {
        this.orders = new HashMap<>();
    }
}
