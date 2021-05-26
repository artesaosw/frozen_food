package com.capgemini.engineering.ddd.frozen_food.stock.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderIngredientDTO;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ChefOrder;

import java.util.HashMap;
import java.util.Map;

public class ChefOrderIngredientConverter {

    public static ChefOrderIngredientDTO chefOrder2ChefOrderIngredientDTO(ChefOrder chefOrder) throws NullPointerException {
        ChefOrderIngredientDTO chefOrderIngredientDTO = new ChefOrderIngredientDTO();
        chefOrderIngredientDTO.setId(chefOrder.getId());
        Map<String, Integer> ordersDTO = new HashMap<>(chefOrder.getOrders());
        chefOrderIngredientDTO.setOrders(ordersDTO);
        return chefOrderIngredientDTO;
    }

    public static ChefOrder chefOrderIngredientDTO2ChefOrder(ChefOrderIngredientDTO chefOrderIngredientDTO) throws NullPointerException {
        Map<String, Integer> orders = new HashMap<>(chefOrderIngredientDTO.getOrders());
        ChefOrder chefOrder = new ChefOrder(chefOrderIngredientDTO.getId(), orders);
        return chefOrder;
    }
}
