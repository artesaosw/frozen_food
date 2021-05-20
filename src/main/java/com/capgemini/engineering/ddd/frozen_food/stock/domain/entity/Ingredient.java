package com.capgemini.engineering.ddd.frozen_food.stock.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.IngredientStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@Document(collection = "ingredient_stock")
public class Ingredient implements AggregateRoot, Serializable {

    private static final Integer INGREDIENT_STOCK_ON_CREATION = 0;
    private static final Integer MINIMUM_STOCK_VALUE_ON_CREATION = 0;

    @Id
    String id;

    private IngredientID ingredientID;

    @NotBlank
    private String name;

    @NotNull
    private Unit unit;

    private Integer minimumStockValue;

    private IngredientStatus ingredientStatus;

    private Integer ingredientStock;

    public Ingredient(@NotBlank String name, @NotNull Unit unit) {
        this.ingredientID = Identificator.newInstance(IngredientID.class);
        this.name = name;
        this.unit = unit;
        this.minimumStockValue = MINIMUM_STOCK_VALUE_ON_CREATION;
        this.ingredientStatus = IngredientStatus.IN_TEST;
        this.ingredientStock = INGREDIENT_STOCK_ON_CREATION;
    }

    public IngredientID id() {
        return this.ingredientID;
    }

    @Override
    public boolean isEqualsTo(Object other) {
        return AggregateRoot.super.isEqualsTo(other);
    }

    @Override
    public int hashcode() {
        return AggregateRoot.super.hashcode();
    }

    public void increaseIngredientStock(@NotNull @Positive Integer quantity) {
        setIngredientStock(this.ingredientStock + quantity);
    }

    public void decreaseIngredientStock(@NotNull @Positive Integer quantity) {
        if (quantity > this.ingredientStock) {
            setIngredientStock(this.ingredientStock - quantity);
        } else {
            setIngredientStock(0);
        }
    }
}
