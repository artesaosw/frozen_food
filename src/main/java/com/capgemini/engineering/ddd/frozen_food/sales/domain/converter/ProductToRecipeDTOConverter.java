package com.capgemini.engineering.ddd.frozen_food.sales.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_sales.RecipeDTO;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Product;

public class ProductToRecipeDTOConverter {

    public static RecipeDTO convertProductToRecipeDTO(Product product) {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setProductID(product.getProductID());
        recipeDTO.setDimensions(product.getDimensions());
        recipeDTO.setName(product.getName());
        recipeDTO.setShelfLife(product.getShelfLife());
        recipeDTO.setAvailable(product.isAvailable());

        return recipeDTO;
    }

}
