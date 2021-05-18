package com.capgemini.engineering.ddd.frozen_food.domain.producao.dto;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.DemandaID;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.entity.Ingredient;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class DemandaDTO {

    @JsonProperty(value = "id")
    private DemandaID demandaID;

    @JsonProperty(value = "articles")
    Map<Ingredient, Integer> articles;

}
