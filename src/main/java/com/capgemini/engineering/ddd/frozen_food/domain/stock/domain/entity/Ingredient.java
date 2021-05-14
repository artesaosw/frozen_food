package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.IngredientStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Ingredient implements AggregateRoot, Serializable {

    private static final Integer INGREDIENT_STOCK_ON_CREATION = 0;

    // TODO o id tem de ser igual ao id do Ingredient do package menu
    private IngredientID id;

    private String name;

    private Unit unit;

    private Integer minimumStockValue;

    private IngredientStatus ingredientStatus;

    private Integer ingredientStock;

    public Ingredient(@NotBlank String name, @NotNull Unit unit, Integer minimumStockValue) {
        this.id = Identificator.newInstance(IngredientID.class);
        this.name = name;
        this.unit = unit;
        this.minimumStockValue = minimumStockValue;
        this.ingredientStatus = IngredientStatus.INTEST;
        this.ingredientStock = INGREDIENT_STOCK_ON_CREATION;
    }

    public IngredientID id() {
        return this.id;
    }

    @Override
    public boolean isEqualsTo(Object other) {
        return AggregateRoot.super.isEqualsTo(other);
    }

    @Override
    public int hashcode() {
        return AggregateRoot.super.hashcode();
    }
}
