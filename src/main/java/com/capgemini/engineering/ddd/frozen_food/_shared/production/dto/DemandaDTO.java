package com.capgemini.engineering.ddd.frozen_food._shared.production.dto;

import com.capgemini.engineering.ddd.frozen_food._shared.id.DemandaID;
import com.capgemini.engineering.ddd.frozen_food.production.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.production.valueObject.ProductionOrderState;
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
