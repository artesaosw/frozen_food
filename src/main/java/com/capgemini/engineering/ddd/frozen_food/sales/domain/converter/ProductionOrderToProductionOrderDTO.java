package com.capgemini.engineering.ddd.frozen_food.sales.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_sales.ProductionOrderDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_sales.RecipeDTO;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Product;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.ProductionOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductionOrderToProductionOrderDTO {

    public static ProductionOrderDTO convertPOrderToPOrderDTO(ProductionOrder pOrder) {
        ProductionOrderDTO pOrderDTO = new ProductionOrderDTO();

        //Clone productionOrderID (or maybe not)
        //pOrderDTO.setProductionOrderID(Identificator.clone(pOrder.getProductionOrderID()));
        pOrderDTO.setProductionOrderID(pOrder.getProductionOrderID());

        Map<String, Integer> recipes = convertProductList(pOrder);
        pOrderDTO.setRecipes(recipes);

        return pOrderDTO;
    }

    /*
    Returns a Map<String, Integer> based on the ProductionOrder passed as argument.
     */
    private static Map<String, Integer> convertProductList(ProductionOrder pOrder) {

        Map<String, Integer> recipes = new HashMap<>();

        //use the GlobalID of a product as a String key in the map
        for (int i = 0; i < pOrder.getProducts().size(); i++) {
            recipes.put(pOrder.getProducts().get(i).getId(), pOrder.getQuantities().get(i));
        }
        return recipes;
    }
}
