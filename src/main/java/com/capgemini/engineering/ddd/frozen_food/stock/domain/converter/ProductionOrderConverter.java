package com.capgemini.engineering.ddd.frozen_food.stock.domain.converter;

import com.capgemini.engineering.ddd.frozen_food.stock.domain.dto.ProductionOrderDTO;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ProductionOrder;

import java.util.ArrayList;
import java.util.List;

public class ProductionOrderConverter {

    public static ProductionOrderDTO productionOrder2ProductionOrderDTO(ProductionOrder productionOrder) throws NullPointerException {
        ProductionOrderDTO productionOrderDTO = new ProductionOrderDTO();
        productionOrderDTO.setOrderReference(productionOrder.getOrderReference());
        productionOrderDTO.setOrders(productionOrder.getOrders());
        return productionOrderDTO;
    }

    public static ProductionOrder productionOrderDTO2ProductionOrder(ProductionOrderDTO productionOrderDTO) throws NullPointerException {
        ProductionOrder productionOrder = new ProductionOrder();
        productionOrder.setOrderReference(productionOrderDTO.getOrderReference());
        productionOrder.setOrders(productionOrderDTO.getOrders());
        return productionOrder;
    }

    public static List<ProductionOrderDTO> productionOrderList2ProductionOrderListDTO(List<ProductionOrder> productionOrderList) throws NullPointerException {
        List<ProductionOrderDTO> productionOrderDTOList = new ArrayList<>();
        for (ProductionOrder productionOrder : productionOrderList) {
            ProductionOrderDTO productionOrderDTO = productionOrder2ProductionOrderDTO(productionOrder);
            productionOrderDTOList.add(productionOrderDTO);
        }
        return productionOrderDTOList;
    }

    public static List<ProductionOrder> productionOrderListDTO2ProductionOrderList(List<ProductionOrderDTO> productionOrderDTOList) throws NullPointerException {
        List<ProductionOrder> productionOrderList = new ArrayList<>();
        for (ProductionOrderDTO productionOrderDTO : productionOrderDTOList) {
            ProductionOrder productionOrder = productionOrderDTO2ProductionOrder(productionOrderDTO);
            productionOrderList.add(productionOrder);
        }
        return productionOrderList;
    }
}
