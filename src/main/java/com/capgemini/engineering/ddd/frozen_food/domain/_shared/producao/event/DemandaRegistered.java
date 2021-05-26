package com.capgemini.engineering.ddd.frozen_food.domain._shared.producao.event;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;

//faltam metodos get
public class DemandaRegistered extends DomainEvent {

    private DemandaID demandaID;


    public DemandaRegistered(DemandaID demandaID){
        super();
        this.demandaID = demandaID;
    }
}
