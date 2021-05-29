package com.capgemini.engineering.ddd.frozen_food.stock.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock.ProductionOrderDTO;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ProductionOrder;

public class ProductionOrderConverter {

    public static ProductionOrderDTO productionOrder2ProductionOrderDTO(ProductionOrder productionOrder) throws NullPointerException {
        ProductionOrderDTO productionOrderDTO = new ProductionOrderDTO();
        productionOrderDTO.setId(productionOrder.getId());
        productionOrderDTO.setOrderReference(productionOrder.getOrderReference());
        productionOrderDTO.setOrders(productionOrder.getOrders());
        return productionOrderDTO;
    }

    public static ProductionOrder productionOrderDTO2ProductionOrder(ProductionOrderDTO productionOrderDTO) throws NullPointerException {
        ProductionOrder productionOrder = new ProductionOrder(productionOrderDTO.getId(), productionOrderDTO.getOrderReference(), productionOrderDTO.getOrders());
        return productionOrder;
    }
}
