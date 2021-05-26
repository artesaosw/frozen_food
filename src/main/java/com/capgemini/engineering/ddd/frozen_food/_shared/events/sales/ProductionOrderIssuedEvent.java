package com.capgemini.engineering.ddd.frozen_food._shared.events.sales;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_sales.ProductionOrderDTO;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.ProductionOrder;
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
