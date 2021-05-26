package com.capgemini.engineering.ddd.frozen_food._shared.production.event;

import com.capgemini.engineering.ddd.frozen_food._shared.id.DemandaID;

public class DemandaCancelled {

    private DemandaID demandaID;


    public DemandaCancelled(DemandaID demandaID){
        super();
        this.demandaID = demandaID;
    }
}
