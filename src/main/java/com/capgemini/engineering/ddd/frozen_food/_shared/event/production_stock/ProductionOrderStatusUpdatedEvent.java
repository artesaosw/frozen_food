package com.capgemini.engineering.ddd.frozen_food._shared.event.production_stock;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock.ProductionOrderStatusDTO;
import org.springframework.context.ApplicationEvent;

public class ProductionOrderStatusUpdatedEvent extends ApplicationEvent {

    private ProductionOrderStatusDTO productionOrderStatusDTO;

    public ProductionOrderStatusUpdatedEvent(Object source, ProductionOrderStatusDTO productionOrderStatusDTO) {
        super(source);
        this.productionOrderStatusDTO = productionOrderStatusDTO;
    }

    public ProductionOrderStatusDTO getProductionOrderStatusDTO() {
        return productionOrderStatusDTO;
    }
}
