package com.capgemini.engineering.ddd.frozen_food._shared.dto.production_sales;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class ProductionOrderDTO {

    @NotNull
    private ProductionOrderID productionOrderID;

//    @NotEmpty
//    private List<RecipeDTO> recipes;
//
//    @NotEmpty
//    private List<Integer> quantities;

    @NotEmpty
    private Map<String, Integer> recipes;

    public ProductionOrderDTO() {

    }

    public ProductionOrderID getProductionOrderID() {
        return productionOrderID;
    }

    public void setProductionOrderID(ProductionOrderID productionOrderID) {
        this.productionOrderID = productionOrderID;
    }

//    public List<RecipeDTO> getRecipes() {
//        return recipes;
//    }
//
//    public void setRecipes(List<RecipeDTO> recipes) {
//        this.recipes = recipes;
//    }
//
//    public List<Integer> getQuantities() {
//        return quantities;
//    }
//
//    public void setQuantities(List<Integer> quantities) {
//        this.quantities = quantities;
//    }

    public Map<String, Integer> getRecipes() {
        return recipes;
    }

    public void setRecipes(Map<String, Integer> recipes) {
        this.recipes = recipes;
    }
}
