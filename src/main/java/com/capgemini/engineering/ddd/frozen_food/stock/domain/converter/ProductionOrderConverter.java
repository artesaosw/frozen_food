package com.capgemini.engineering.ddd.frozen_food.stock.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock.ProductionOrderDTO;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ProductionOrder;

import java.util.HashMap;
import java.util.Map;

public class ProductionOrderConverter {

    public static ProductionOrderDTO productionOrder2ProductionOrderDTO(ProductionOrder productionOrder) throws NullPointerException {
        ProductionOrderDTO productionOrderDTO = new ProductionOrderDTO();
        productionOrderDTO.setId(productionOrder.getId());
        productionOrderDTO.setOrderReference(productionOrder.getOrderReference());
        Map<String, Integer> ordersDTO = new HashMap<>(productionOrder.getOrders());
        productionOrderDTO.setOrders(ordersDTO);
        return productionOrderDTO;
    }

    public static ProductionOrder productionOrderDTO2ProductionOrder(ProductionOrderDTO productionOrderDTO) throws NullPointerException {
        Map<String, Integer> orders = new HashMap<>(productionOrderDTO.getOrders());
        ProductionOrder productionOrder = new ProductionOrder(productionOrderDTO.getId(), productionOrderDTO.getOrderReference(), orders);
        return productionOrder;
    }
}
