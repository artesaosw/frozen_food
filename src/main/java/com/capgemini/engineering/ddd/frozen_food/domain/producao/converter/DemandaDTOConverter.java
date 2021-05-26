package com.capgemini.engineering.ddd.frozen_food.domain.producao.converter;


import com.capgemini.engineering.ddd.frozen_food.domain._shared.producao.dto.DemandaDTO;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Demanda;

public class DemandaDTOConverter {


    public DemandaDTO demanda2DemandaDTO(Demanda demanda){

       DemandaDTO demandaDTO = new DemandaDTO();
       //demandaDTO.setDemandaID();


       return demandaDTO;
    }
}
