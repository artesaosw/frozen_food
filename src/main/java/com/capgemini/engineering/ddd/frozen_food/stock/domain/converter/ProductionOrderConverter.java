package com.capgemini.engineering.ddd.frozen_food.stock.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.IngredientDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock.ProductionOrderDTO;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ProductionOrder;
import static com.capgemini.engineering.ddd.frozen_food.stock.domain.converter.IngredientConverter.ingredient2IngredientDTO;
import static com.capgemini.engineering.ddd.frozen_food.stock.domain.converter.IngredientConverter.ingredientDTO2Ingredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductionOrderConverter {

    public static ProductionOrderDTO productionOrder2ProductionOrderDTO(ProductionOrder productionOrder) throws NullPointerException {
        ProductionOrderDTO productionOrderDTO = new ProductionOrderDTO();
        productionOrderDTO.setOrderReference(productionOrder.getOrderReference());
        Map<IngredientDTO, Integer> orders = new HashMap<>();
        for (Map.Entry<Ingredient, Integer> map : productionOrder.getOrders().entrySet()) {
            IngredientDTO ingredientDTO = ingredient2IngredientDTO(map.getKey());
            Integer quantity = map.getValue();
            orders.put(ingredientDTO,quantity);
        }
        productionOrderDTO.setOrders(orders);
        return productionOrderDTO;
    }

    public static ProductionOrder productionOrderDTO2ProductionOrder(ProductionOrderDTO productionOrderDTO) throws NullPointerException {
        Map<Ingredient, Integer> orders = new HashMap<>();
        for (Map.Entry<IngredientDTO, Integer> map : productionOrderDTO.getOrders().entrySet()) {
            Ingredient ingredient = ingredientDTO2Ingredient(map.getKey());
            Integer quantity = map.getValue();
            orders.put(ingredient,quantity);
        }
        ProductionOrder productionOrder = new ProductionOrder(productionOrderDTO.getOrderReference(), orders);
        return productionOrder;
    }

    public static List<ProductionOrderDTO> productionOrderList2ProductionOrderListDTO(List<ProductionOrder> productionOrderList) throws NullPointerException {
        List<ProductionOrderDTO> productionOrderDTOList = new ArrayList<>();
        for (ProductionOrder productionOrder : productionOrderList) {
            ProductionOrderDTO productionOrderDTO = productionOrder2ProductionOrderDTO(productionOrder);
            productionOrderDTOList.add(productionOrderDTO);
        }
        return productionOrderDTOList;
    }

    public static List<ProductionOrder> productionOrderListDTO2ProductionOrderList(List<ProductionOrderDTO> productionOrderDTOList) throws NullPointerException {
        List<ProductionOrder> productionOrderList = new ArrayList<>();
        for (ProductionOrderDTO productionOrderDTO : productionOrderDTOList) {
            ProductionOrder productionOrder = productionOrderDTO2ProductionOrder(productionOrderDTO);
            productionOrderList.add(productionOrder);
        }
        return productionOrderList;
    }
}
