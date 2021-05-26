package com.capgemini.engineering.ddd.frozen_food.stock.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock.ProductionOrderIngredientDTO;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ProductionOrder;

import java.util.HashMap;
import java.util.Map;

public class ProductionOrderIngredientConverter {

    public static ProductionOrderIngredientDTO productionOrder2ProductionOrderIngredientDTO(ProductionOrder productionOrder) throws NullPointerException {
        ProductionOrderIngredientDTO productionOrderIngredientDTO = new ProductionOrderIngredientDTO();
        productionOrderIngredientDTO.setId(productionOrder.getId());
        Map<String, Integer> ordersDTO = new HashMap<>(productionOrder.getOrders());
        productionOrderIngredientDTO.setOrders(ordersDTO);
        return productionOrderIngredientDTO;
    }

    public static ProductionOrder productionOrderIngredientDTO2ProductionOrder(ProductionOrderIngredientDTO productionOrderIngredientDTO) throws NullPointerException {
        Map<String, Integer> orders = new HashMap<>(productionOrderIngredientDTO.getOrders());
        ProductionOrder productionOrder = new ProductionOrder(productionOrderIngredientDTO.getId(), orders);
        return productionOrder;
    }
}
