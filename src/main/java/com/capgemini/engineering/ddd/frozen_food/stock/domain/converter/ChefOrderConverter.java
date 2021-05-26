package com.capgemini.engineering.ddd.frozen_food.stock.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderDTO;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ChefOrder;

import java.util.HashMap;
import java.util.Map;

public class ChefOrderConverter {

    public static ChefOrderDTO chefOrder2ChefOrderDTO(ChefOrder chefOrder) throws NullPointerException {
        ChefOrderDTO chefOrderDTO = new ChefOrderDTO();
        chefOrderDTO.setId(chefOrder.getId());
        chefOrderDTO.setOrderReference(chefOrder.getOrderReference());
        Map<String, Integer> ordersDTO = new HashMap<>(chefOrder.getOrders());
        chefOrderDTO.setOrders(ordersDTO);
        return chefOrderDTO;
    }

    public static ChefOrder chefOrderDTO2ChefOrder(ChefOrderDTO chefOrderDTO) throws NullPointerException {
        Map<String, Integer> orders = new HashMap<>(chefOrderDTO.getOrders());
        ChefOrder chefOrder = new ChefOrder(chefOrderDTO.getId(), chefOrderDTO.getOrderReference(), orders);
        return chefOrder;
    }
}
