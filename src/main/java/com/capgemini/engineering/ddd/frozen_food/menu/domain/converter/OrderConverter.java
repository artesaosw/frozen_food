package com.capgemini.engineering.ddd.frozen_food.menu.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderDTO;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderConverter {

    public static ChefOrderDTO order2chefOrderDTO(Order order) {
        ChefOrderDTO chefOrderDTO = new ChefOrderDTO();
        chefOrderDTO.setId(order.getId());
        chefOrderDTO.setOrderReference(order.getOrderReference());
        Map<String, Integer> orders = new HashMap<>(order.getOrders());
        chefOrderDTO.setOrders(orders);
        return chefOrderDTO;
    }
}
