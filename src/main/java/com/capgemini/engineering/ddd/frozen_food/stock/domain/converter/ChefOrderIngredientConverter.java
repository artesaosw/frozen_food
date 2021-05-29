package com.capgemini.engineering.ddd.frozen_food.stock.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderIngredientDTO;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ChefOrder;

public class ChefOrderIngredientConverter {

    public static ChefOrderIngredientDTO chefOrder2ChefOrderIngredientDTO(ChefOrder chefOrder) throws NullPointerException {
        ChefOrderIngredientDTO chefOrderIngredientDTO = new ChefOrderIngredientDTO();
        chefOrderIngredientDTO.setId(chefOrder.getId());
        chefOrderIngredientDTO.setOrders(chefOrder.getOrders());
        return chefOrderIngredientDTO;
    }

    public static ChefOrder chefOrderIngredientDTO2ChefOrder(ChefOrderIngredientDTO chefOrderIngredientDTO) throws NullPointerException {
        ChefOrder chefOrder = new ChefOrder(chefOrderIngredientDTO.getId(), chefOrderIngredientDTO.getOrders());
        return chefOrder;
    }
}
