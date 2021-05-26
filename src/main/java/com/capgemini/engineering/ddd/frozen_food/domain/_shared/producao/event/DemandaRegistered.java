package com.capgemini.engineering.ddd.frozen_food.domain._shared.producao.event;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.producao.dto.DemandaDTO;

//faltam metodos get
public class DemandaRegistered extends DomainEvent {

    private DemandaDTO demandaDTO


    public DemandaRegistered(DemandaDTO demandaDTO){
        super();
        this.demandaDTO= demandaDTO;
    }
}
