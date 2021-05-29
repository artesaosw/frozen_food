package com.capgemini.engineering.ddd.frozen_food.stock.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock.ProductionOrderIngredientDTO;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ProductionOrder;

public class ProductionOrderIngredientConverter {

    public static ProductionOrderIngredientDTO productionOrder2ProductionOrderIngredientDTO(ProductionOrder productionOrder) throws NullPointerException {
        ProductionOrderIngredientDTO productionOrderIngredientDTO = new ProductionOrderIngredientDTO();
        productionOrderIngredientDTO.setId(productionOrder.getId());
        productionOrderIngredientDTO.setOrders(productionOrder.getOrders());
        return productionOrderIngredientDTO;
    }

    public static ProductionOrder productionOrderIngredientDTO2ProductionOrder(ProductionOrderIngredientDTO productionOrderIngredientDTO) throws NullPointerException {
        ProductionOrder productionOrder = new ProductionOrder(productionOrderIngredientDTO.getId(), productionOrderIngredientDTO.getOrders());
        return productionOrder;
    }
}
