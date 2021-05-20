package com.capgemini.engineering.ddd.frozen_food.domain._shared.producao.event;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;
import lombok.Getter;

@Getter
public class DemandaClosed extends DomainEvent {

    private DemandaID demandaID;


        public DemandaClosed(DemandaID demandaID){
            super();
            this.demandaID = demandaID;
        }

    }

