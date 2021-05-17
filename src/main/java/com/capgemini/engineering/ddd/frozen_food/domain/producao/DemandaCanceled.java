package com.capgemini.engineering.ddd.frozen_food.domain.producao;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;

public class DemandaCanceled {

    private DemandaID demandaID;


    public DemandaCanceled(DemandaID demandaID){
        super();
        this.demandaID = demandaID;
    }
}
