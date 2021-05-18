package com.capgemini.engineering.ddd.frozen_food.domain.producao.dto;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RecipeID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Unit;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
public class ProducedRecipeDTO {

    @JsonProperty(value = "recipeID")
    private RecipeID recipeID;

    @JsonProperty(value = "batchID")
    private BatchID id;

    @JsonProperty(value = "unit")
    private Unit unit;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "quantity")
    private int quantity;

    @JsonProperty(value = "prazoValidade")
    private LocalDate prazoValidade;

    @JsonProperty(value = "dataProducao")
    private LocalDate dataProducao;

    @JsonProperty(value = "tipoReceita")
    private String tipoReceita;

    public ProducedRecipeDTO(){

    }

    public ProducedRecipeDTO(RecipeID recipeID, BatchID id, Unit unit, String name, int quantity, LocalDate prazoValidade, LocalDate dataProducao, String tipoReceita ){
        this.recipeID = recipeID;
        this.id = id;
        this.unit = unit;
        this.name = name;
        this.quantity = quantity;
        this.prazoValidade = prazoValidade;
        this.dataProducao = dataProducao;
        this.tipoReceita = tipoReceita;
    }
}
