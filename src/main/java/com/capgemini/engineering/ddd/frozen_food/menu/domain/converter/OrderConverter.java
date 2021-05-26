package com.capgemini.engineering.ddd.frozen_food.menu.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.IngredientDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderDTO;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Order;

import java.util.HashMap;
import java.util.Map;

import static com.capgemini.engineering.ddd.frozen_food.menu.domain.converter.IngredientConverter.ingredient2IngredientDTO;

public class OrderConverter {

    public static ChefOrderDTO order2chefOrderDTO(Order order) {
        ChefOrderDTO chefOrderDTO = new ChefOrderDTO();
        chefOrderDTO.setId(order.getId());
        chefOrderDTO.setOrderReference(order.getOrderReference());

        Map<IngredientDTO, Integer> orders = new HashMap<>();
        for (Map.Entry<Ingredient, Integer> map : order.getOrders().entrySet()) {
            IngredientDTO ingredientDTO = ingredient2IngredientDTO(map.getKey());
            Integer quantity = map.getValue();
            orders.put(ingredientDTO,quantity);
         }

        chefOrderDTO.setOrders(orders);
        return chefOrderDTO;

    }
}
