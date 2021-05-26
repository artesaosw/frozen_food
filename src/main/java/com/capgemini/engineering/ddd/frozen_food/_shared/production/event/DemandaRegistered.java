package com.capgemini.engineering.ddd.frozen_food._shared.production.event;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.production.dto.DemandaDTO;

//faltam metodos get
public class DemandaRegistered extends DomainEvent {

    private DemandaDTO demandaDTO;


    public DemandaRegistered(DemandaDTO demandaDTO){
        super();
        this.demandaDTO= demandaDTO;
    }
}
