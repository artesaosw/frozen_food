package com.capgemini.engineering.ddd.frozen_food.production.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.production.dto.DemandaDTO;
import com.capgemini.engineering.ddd.frozen_food.production.entity.Demanda;

public class DemandaDTOConverter {


    public DemandaDTO demanda2DemandaDTO(Demanda demanda){

       DemandaDTO demandaDTO = new DemandaDTO();
       //demandaDTO.setDemandaID();


       return demandaDTO;
    }
}
