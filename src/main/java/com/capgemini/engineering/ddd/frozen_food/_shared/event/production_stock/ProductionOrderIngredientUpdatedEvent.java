package com.capgemini.engineering.ddd.frozen_food._shared.event.production_stock;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock.ProductionOrderIngredientDTO;
import org.springframework.context.ApplicationEvent;

public class ProductionOrderIngredientUpdatedEvent extends ApplicationEvent {

    private ProductionOrderIngredientDTO  productionOrderIngredientDTO;

    public ProductionOrderIngredientUpdatedEvent(Object source, ProductionOrderIngredientDTO  productionOrderIngredientDTO) {
        super(source);
        this.productionOrderIngredientDTO = productionOrderIngredientDTO;
    }

    public ProductionOrderIngredientDTO getProductionOrderIngredientDTO() {
        return productionOrderIngredientDTO;
    }
}
