package com.capgemini.engineering.ddd.frozen_food.domain.stock.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.IngredientStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
public class Ingredient implements AggregateRoot, Serializable {

    // TODO o id tem de ser igual ao id do Ingredient do package menu

    private IngredientID id;

    private String name;

    private Unit unit;

    @Setter
    private Integer minimumStockValue;

    @Setter
    private IngredientStatus ingredientStatus;

    protected Ingredient() {
    }

    public Ingredient(@NotBlank String name, @NotNull Unit unit, Integer minimumStockValue) {
        this.id = Identificator.newInstance(IngredientID.class);
        this.name = name;
        this.unit = unit;
        this.minimumStockValue = minimumStockValue;
        this.ingredientStatus = IngredientStatus.INUSE;
    }

    public IngredientID id() {
        return this.id;
    }
}
