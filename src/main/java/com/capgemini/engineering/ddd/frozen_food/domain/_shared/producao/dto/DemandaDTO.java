package com.capgemini.engineering.ddd.frozen_food.domain._shared.producao.dto;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Ingredient;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
//Preciso enviar Demandaid?
public class DemandaDTO {

    @JsonProperty(value = "id")
    private DemandaID demandaID;

    @JsonProperty(value = "articles")
    Map<Ingredient, Integer> articles;

    @JsonProperty(value = "unit")
    private Unit unit;

    public DemandaDTO(){

    }

    public DemandaDTO(DemandaID demandaID, Map<Ingredient, Integer> articles, Unit unit){
        this.demandaID = demandaID;
        this.articles = articles;
        this.unit = unit;

    }

}
