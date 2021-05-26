package com.capgemini.engineering.ddd.frozen_food.domain.producao.converter;


import com.capgemini.engineering.ddd.frozen_food.domain._shared.producao.dto.ProductionOrderDTO;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.ProductionOrder;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.valueObject.Dimensions;

public class ProductionOrderDTOConverter {

    public ProductionOrderDTO productionOrder2productionOrderDTO(ProductionOrder productionOrder){

        ProductionOrderDTO productionOrderDTO = new ProductionOrderDTO();
        productionOrderDTO.setProductionOrderID(productionOrder.getProductionOrderID());
        productionOrderDTO.setProductionDate(productionOrder.getProductionDate());
        productionOrderDTO.setProducts(productionOrder.getProducts());
        productionOrderDTO.setProductionOrderState(productionOrder.productionOrderState());
        productionOrderDTO.setId(productionOrder.getId());
        productionOrderDTO.setDimensions(new Dimensions(productionOrder.getDimensions().getLength(), productionOrder.getDimensions().getWidth(),
                productionOrder.getDimensions().getHeight(), productionOrder.getDimensions().getWeight()));
        return productionOrderDTO;
    }
}
