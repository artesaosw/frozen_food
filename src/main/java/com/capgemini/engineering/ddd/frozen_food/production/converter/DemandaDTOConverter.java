package com.capgemini.engineering.ddd.frozen_food.production.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock.ProductionOrderDTO;
import com.capgemini.engineering.ddd.frozen_food.production.entity.Demanda;

public class DemandaDTOConverter {


    public ProductionOrderDTO demanda2DemandaDTO(Demanda demanda){

        ProductionOrderDTO demandaDTO = new ProductionOrderDTO();
       //demandaDTO.setDemandaID();


       return demandaDTO;
    }
}
