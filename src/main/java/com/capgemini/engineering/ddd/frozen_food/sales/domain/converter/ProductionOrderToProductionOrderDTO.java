package com.capgemini.engineering.ddd.frozen_food.sales.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_sales.ProductionOrderDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_sales.RecipeDTO;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Product;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.ProductionOrder;

import java.util.ArrayList;
import java.util.List;

public class ProductionOrderToProductionOrderDTO {

    public static ProductionOrderDTO convertPOrderToPOrderDTO(ProductionOrder pOrder) {
        ProductionOrderDTO pOrderDTO = new ProductionOrderDTO();

        //Clone productionOrderID (or maybe not)
        //pOrderDTO.setProductionOrderID(Identificator.clone(pOrder.getProductionOrderID()));
        pOrderDTO.setProductionOrderID(pOrder.getProductionOrderID());

        List<RecipeDTO> recipes = convertProductList(pOrder.getProducts());
        pOrderDTO.setRecipes(recipes);
        pOrderDTO.setQuantities(pOrder.getQuantities());

        return pOrderDTO;
    }

    /*
    Returns a List<RecipeDTO> based on the List<Product> passed as argument.
     */
    private static List<RecipeDTO> convertProductList(List<Product> originalList) {

        List<RecipeDTO> listRecipeDTO = new ArrayList<>();

        for(Product product : originalList) {
            listRecipeDTO.add(ProductToRecipeDTOConverter.convertProductToRecipeDTO(product));
        }

        return listRecipeDTO;
    }
}
