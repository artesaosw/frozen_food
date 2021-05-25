package com.capgemini.engineering.ddd.frozen_food.stock.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock.ProductionOrderStatusDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ProductionOrder;

public class ProductionOrderStatusConverter {

    public static ProductionOrderStatusDTO productionOrder2ProductionOrderStatusDTO(ProductionOrder productionOrder) throws NullPointerException {
        ProductionOrderStatusDTO productionOrderStatusDTO = new ProductionOrderStatusDTO();
        productionOrderStatusDTO.setId(Identificator.clone(productionOrder.getId()));
        productionOrderStatusDTO.setOrderStatus(productionOrder.getOrderStatus());
        return productionOrderStatusDTO;
    }

    public static ProductionOrder productionOrderStatusDTO2ProductionOrder(ProductionOrderStatusDTO productionOrderStatusDTO) throws NullPointerException {
        ProductionOrder productionOrder = new ProductionOrder(Identificator.clone(productionOrderStatusDTO.getId()), productionOrderStatusDTO.getOrderStatus());
        return productionOrder;
    }
}
