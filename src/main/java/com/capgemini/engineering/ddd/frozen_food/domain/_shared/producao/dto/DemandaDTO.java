package com.capgemini.engineering.ddd.frozen_food.domain._shared.producao.dto;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductionOrderState;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Ingredient;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.time.LocalDate;
import java.util.Map;

@Data
//Preciso enviar Demandaid?
public class DemandaDTO {

    @JsonProperty(value = "id")
    private DemandaID demandaID;

    @JsonProperty(value = "articles")
    Map<Ingredient, Integer> articles;

    @JsonProperty(value = "status")
    private ProductionOrderState status;

    @JsonProperty(value = "localDate")
    private LocalDate dataDemanda;


    public DemandaDTO(){

    }

}
