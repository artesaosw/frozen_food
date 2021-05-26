package com.capgemini.engineering.ddd.frozen_food.stock.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderStatusDTO;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ChefOrder;

public class ChefOrderStatusConverter {

    public static ChefOrderStatusDTO chefOrder2ChefOrderStatusDTO(ChefOrder chefOrder) throws NullPointerException {
        ChefOrderStatusDTO chefOrderStatusDTO = new ChefOrderStatusDTO();
        chefOrderStatusDTO.setId(chefOrder.getId());
        chefOrderStatusDTO.setOrderStatus(chefOrder.getOrderStatus());
        return chefOrderStatusDTO;
    }

    public static ChefOrder chefOrderStatusDTO2ChefOrder(ChefOrderStatusDTO chefOrderStatusDTO) throws NullPointerException {
        ChefOrder chefOrder = new ChefOrder(chefOrderStatusDTO.getId(), chefOrderStatusDTO.getOrderStatus());
        return chefOrder;
    }
}
