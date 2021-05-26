package com.capgemini.engineering.ddd.frozen_food._shared.production.event;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.id.DemandaID;
import lombok.Getter;

@Getter
public class DemandaComplete extends DomainEvent {

    private DemandaID demandaID;


        public DemandaComplete(DemandaID demandaID){
            super();
            this.demandaID = demandaID;
        }

    }

