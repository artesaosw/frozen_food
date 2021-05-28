package com.capgemini.engineering.ddd.frozen_food._shared.event.sales_production;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_production.ProductionOrderDTO;
import org.springframework.context.ApplicationEvent;

public class ProductionOrderIssuedEvent extends ApplicationEvent {

    private ProductionOrderDTO productionOrderDTO;

    public ProductionOrderIssuedEvent(Object source, ProductionOrderDTO productionOrderDTO) {
        super(source);
        this.productionOrderDTO = productionOrderDTO;
    }

    public ProductionOrderDTO getProductionOrder() {
        return this.productionOrderDTO;
    }
}
