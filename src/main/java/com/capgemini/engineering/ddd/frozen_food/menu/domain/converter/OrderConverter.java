package com.capgemini.engineering.ddd.frozen_food.menu.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderIngredientDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderStatusDTO;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Order;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ChefOrder;

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

    public static ChefOrderIngredientDTO order2ChefOrderIngredientDTO(Order order) throws NullPointerException {
        ChefOrderIngredientDTO chefOrderIngredientDTO = new ChefOrderIngredientDTO();
        chefOrderIngredientDTO.setId(order.getId());
        Map<String, Integer> ordersDTO = new HashMap<>(order.getOrders());
        chefOrderIngredientDTO.setOrders(ordersDTO);
        return chefOrderIngredientDTO;
    }

    public static ChefOrderStatusDTO order2ChefOrderStatus(Order order) {
        ChefOrderStatusDTO chefOrderStatusDTO = new ChefOrderStatusDTO();
        chefOrderStatusDTO.setId(order.getId());
        chefOrderStatusDTO.setOrderStatus(order.getOrderStatus());
        return chefOrderStatusDTO;
    }
}
