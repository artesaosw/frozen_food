package com.capgemini.engineering.ddd.frozen_food.stock.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.IngredientStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Ingredient implements AggregateRoot, Serializable {

    private static final Integer INGREDIENT_STOCK_ON_CREATION = 0;
    private static final Integer MINIMUM_STOCK_VALUE_ON_CREATION = 0;

    // TODO o id tem de ser igual ao id do Ingredient do package menu
    @Id
    private IngredientID id;

    private String name;

    private Unit unit;

    private Integer minimumStockValue;

    private IngredientStatus ingredientStatus;

    private Integer ingredientStock;

    public Ingredient(@NotBlank String name, @NotNull Unit unit) {
        this.id = Identificator.newInstance(IngredientID.class);
        this.name = name;
        this.unit = unit;
        this.minimumStockValue = MINIMUM_STOCK_VALUE_ON_CREATION;
        this.ingredientStatus = IngredientStatus.IN_TEST;
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

    public void increaseIngredientStock(@NotNull @PositiveOrZero Integer quantity) {
        setIngredientStock(this.ingredientStock + quantity);
    }

    public void decreaseIngredientStock(@NotNull @PositiveOrZero Integer quantity) {
        if (quantity > this.ingredientStock) {
            setIngredientStock(this.ingredientStock - quantity);
        } else {
            setIngredientStock(0);
        }
    }
}
