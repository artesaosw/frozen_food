package com.capgemini.engineering.ddd.frozen_food.stock.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.IngredientDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock.ProductionOrderIngredientDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ProductionOrder;

import java.util.HashMap;
import java.util.Map;

import static com.capgemini.engineering.ddd.frozen_food.stock.domain.converter.IngredientConverter.ingredient2IngredientDTO;
import static com.capgemini.engineering.ddd.frozen_food.stock.domain.converter.IngredientConverter.ingredientDTO2Ingredient;

public class ProductionOrderIngredientConverter {

    public static ProductionOrderIngredientDTO productionOrder2ProductionOrderIngredientDTO(ProductionOrder productionOrder) throws NullPointerException {
        ProductionOrderIngredientDTO productionOrderIngredientDTO = new ProductionOrderIngredientDTO();
        productionOrderIngredientDTO.setId(Identificator.clone(productionOrder.getId()));
        Map<IngredientDTO, Integer> ordersDTO = new HashMap<>();
        for (Map.Entry<Ingredient, Integer> map : productionOrder.getOrders().entrySet()) {
            IngredientDTO ingredientDTO = ingredient2IngredientDTO(map.getKey());
            Integer quantity = map.getValue();
            ordersDTO.put(ingredientDTO,quantity);
        }
        productionOrderIngredientDTO.setOrders(ordersDTO);
        return productionOrderIngredientDTO;
    }

    public static ProductionOrder productionOrderIngredientDTO2ProductionOrder(ProductionOrderIngredientDTO productionOrderIngredientDTO) throws NullPointerException {
        Map<Ingredient, Integer> orders = new HashMap<>();
        for (Map.Entry<IngredientDTO, Integer> map : productionOrderIngredientDTO.getOrders().entrySet()) {
            Ingredient ingredient = ingredientDTO2Ingredient(map.getKey());
            Integer quantity = map.getValue();
            orders.put(ingredient,quantity);
        }
        ProductionOrder productionOrder = new ProductionOrder(Identificator.clone(productionOrderIngredientDTO.getId()), orders);
        return productionOrder;
    }
}
