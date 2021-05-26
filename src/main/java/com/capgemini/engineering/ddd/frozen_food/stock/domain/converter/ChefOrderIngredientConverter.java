package com.capgemini.engineering.ddd.frozen_food.stock.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.IngredientDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderIngredientDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ChefOrder;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;

import java.util.HashMap;
import java.util.Map;

import static com.capgemini.engineering.ddd.frozen_food.stock.domain.converter.IngredientConverter.ingredient2IngredientDTO;
import static com.capgemini.engineering.ddd.frozen_food.stock.domain.converter.IngredientConverter.ingredientDTO2Ingredient;

public class ChefOrderIngredientConverter {

    public static ChefOrderIngredientDTO chefOrder2ChefOrderIngredientDTO(ChefOrder chefOrder) throws NullPointerException {
        ChefOrderIngredientDTO chefOrderIngredientDTO = new ChefOrderIngredientDTO();
        chefOrderIngredientDTO.setId(Identificator.clone(chefOrder.getId()));
        Map<IngredientDTO, Integer> ordersDTO = new HashMap<>();
        for (Map.Entry<Ingredient, Integer> map : chefOrder.getOrders().entrySet()) {
            IngredientDTO ingredientDTO = ingredient2IngredientDTO(map.getKey());
            Integer quantity = map.getValue();
            ordersDTO.put(ingredientDTO,quantity);
        }
        chefOrderIngredientDTO.setOrders(ordersDTO);
        return chefOrderIngredientDTO;
    }

    public static ChefOrder chefOrderIngredientDTO2ChefOrder(ChefOrderIngredientDTO chefOrderIngredientDTO) throws NullPointerException {
        Map<Ingredient, Integer> orders = new HashMap<>();
        for (Map.Entry<IngredientDTO, Integer> map : chefOrderIngredientDTO.getOrders().entrySet()) {
            Ingredient ingredient = ingredientDTO2Ingredient(map.getKey());
            Integer quantity = map.getValue();
            orders.put(ingredient,quantity);
        }
        ChefOrder chefOrder = new ChefOrder(Identificator.clone(chefOrderIngredientDTO.getId()), orders);
        return chefOrder;
    }
}
