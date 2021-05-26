package com.capgemini.engineering.ddd.frozen_food._shared.production.event;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock.ProductionOrderDTO;

//faltam metodos get
public class DemandaRegistered extends DomainEvent {

    private ProductionOrderDTO demandaDTO;


    public DemandaRegistered(ProductionOrderDTO demandaDTO){
        super();
        this.demandaDTO= demandaDTO;
    }
}
