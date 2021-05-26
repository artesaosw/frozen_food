package com.capgemini.engineering.ddd.frozen_food.domain._shared.producao.event;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;

public class DemandaCancelled {

    private DemandaID demandaID;


    public DemandaCancelled(DemandaID demandaID){
        super();
        this.demandaID = demandaID;
    }
}
