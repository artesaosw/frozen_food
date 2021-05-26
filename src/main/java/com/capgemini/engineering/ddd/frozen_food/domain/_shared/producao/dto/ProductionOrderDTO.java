package com.capgemini.engineering.ddd.frozen_food.domain._shared.producao.dto;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.*;
import com.capgemini.engineering.ddd.frozen_food.domain.menu.Recipe;
import com.capgemini.engineering.ddd.frozen_food.domain.producao.valueObject.Dimensions;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@Data
public class ProductionOrderDTO {

    @JsonProperty(value = "ID")
    private String id;

    @JsonProperty(value = "productionOrderID")
    private ProductionOrderID productionOrderID;

    @JsonProperty(value = "products")
    private Map<Recipe, Integer> products = new HashMap<>();

    @JsonProperty(value = "productionOrderState")
    private ProductionOrderState productionOrderState;

    @JsonProperty(value = "productionDate")
    private LocalDate productionDate;

    @JsonProperty(value = "dimensions")
    private Dimensions dimensions;

    public ProductionOrderDTO(){

    }

}
