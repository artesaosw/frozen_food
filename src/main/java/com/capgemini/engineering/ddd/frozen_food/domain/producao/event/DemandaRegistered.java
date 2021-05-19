package com.capgemini.engineering.ddd.frozen_food.domain.producao.event;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;

public class DemandaRegistered extends DomainEvent {

    private DemandaID demandaID;


    public DemandaRegistered(DemandaID demandaID){
        super();
        this.demandaID = demandaID;
    }
}
