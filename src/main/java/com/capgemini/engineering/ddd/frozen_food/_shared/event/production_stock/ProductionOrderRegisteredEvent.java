package com.capgemini.engineering.ddd.frozen_food._shared.event.production_stock;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock.ProductionOrderDTO;
import org.springframework.context.ApplicationEvent;

public class ProductionOrderRegisteredEvent extends ApplicationEvent {

    private ProductionOrderDTO productionOrderDTO;

    public ProductionOrderRegisteredEvent(Object source, ProductionOrderDTO productionOrderDTO) {
        super(source);
        this.productionOrderDTO = productionOrderDTO;
    }

    public ProductionOrderDTO getProductionOrderDTO() {
        return productionOrderDTO;
    }
}
