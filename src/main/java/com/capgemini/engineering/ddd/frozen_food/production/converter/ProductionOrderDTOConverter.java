package com.capgemini.engineering.ddd.frozen_food.production.converter;


import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_sales.ProductionOrderDTO;
import com.capgemini.engineering.ddd.frozen_food.production.entity.ProductionOrder;
import com.capgemini.engineering.ddd.frozen_food.production.valueObject.Dimensions;

public class ProductionOrderDTOConverter {

    public ProductionOrderDTO productionOrder2productionOrderDTO(ProductionOrder productionOrder){

        ProductionOrderDTO productionOrderDTO = new ProductionOrderDTO();
        productionOrderDTO.setProductionOrderID(productionOrder.getProductionOrderID());
        productionOrderDTO.setProductionDate(productionOrder.getProductionDate());
        productionOrderDTO.setProducts(productionOrder.getProducts());
        productionOrderDTO.setProductionOrderState(productionOrder.productionOrderState());
        productionOrderDTO.setId(productionOrder.getProductionOrderID().toString());
        productionOrderDTO.setDimensions(new Dimensions(productionOrder.getDimensions().getLength(), productionOrder.getDimensions().getWidth(),
                productionOrder.getDimensions().getHeight(), productionOrder.getDimensions().getWeight()));
        return productionOrderDTO;
    }
}
