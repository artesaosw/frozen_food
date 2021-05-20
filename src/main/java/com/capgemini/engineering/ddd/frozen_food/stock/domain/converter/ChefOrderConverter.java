package com.capgemini.engineering.ddd.frozen_food.stock.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.stock.dto.ChefOrderDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.stock.dto.IngredientDTO;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ChefOrder;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import static com.capgemini.engineering.ddd.frozen_food.stock.domain.converter.IngredientConverter.ingredient2IngredientDTO;
import static com.capgemini.engineering.ddd.frozen_food.stock.domain.converter.IngredientConverter.ingredientDTO2Ingredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChefOrderConverter {

    public static ChefOrderDTO chefOrder2ChefOrderDTO(ChefOrder chefOrder) throws NullPointerException {
        ChefOrderDTO chefOrderDTO = new ChefOrderDTO();
        chefOrderDTO.setOrderReference(chefOrder.getOrderReference());
        Map<IngredientDTO, Integer> orders = new HashMap<>();
        for (Map.Entry<Ingredient, Integer> map : chefOrder.getOrders().entrySet()) {
            IngredientDTO ingredientDTO = ingredient2IngredientDTO(map.getKey());
            Integer quantity = map.getValue();
            orders.put(ingredientDTO,quantity);
        }
        chefOrderDTO.setOrders(orders);
        return chefOrderDTO;
    }

    public static ChefOrder chefOrderDTO2ChefOrder(ChefOrderDTO chefOrderDTO) throws NullPointerException {
        Map<Ingredient, Integer> orders = new HashMap<>();
        for (Map.Entry<IngredientDTO, Integer> map : chefOrderDTO.getOrders().entrySet()) {
            Ingredient ingredient = ingredientDTO2Ingredient(map.getKey());
            Integer quantity = map.getValue();
            orders.put(ingredient,quantity);
        }
        ChefOrder chefOrder = new ChefOrder(chefOrderDTO.getOrderReference(), orders);
        return chefOrder;
    }

    public static List<ChefOrderDTO> chefOrderList2ChefOrderListDTO(List<ChefOrder> chefOrderList) throws NullPointerException {
        List<ChefOrderDTO> chefOrderDTOList = new ArrayList<>();
        for (ChefOrder chefOrder : chefOrderList) {
            ChefOrderDTO chefOrderDTO = chefOrder2ChefOrderDTO(chefOrder);
            chefOrderDTOList.add(chefOrderDTO);
        }
        return chefOrderDTOList;
    }

    public static List<ChefOrder> chefOrderListDTO2ChefOrderList(List<ChefOrderDTO> chefOrderDTOList) throws NullPointerException {
        List<ChefOrder> chefOrderList = new ArrayList<>();
        for (ChefOrderDTO chefOrderDTO : chefOrderDTOList) {
            ChefOrder chefOrder = chefOrderDTO2ChefOrder(chefOrderDTO);
            chefOrderList.add(chefOrder);
        }
        return chefOrderList;
    }
}
