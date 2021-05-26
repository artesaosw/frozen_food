package com.capgemini.engineering.ddd.frozen_food.stock.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.IngredientStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Validated
@Document(collection = "ingredient_stock")
public class Ingredient implements AggregateRoot, Serializable {

    private static final Integer INGREDIENT_STOCK_ON_CREATION = 0;
    private static final Integer MINIMUM_STOCK_VALUE_ON_CREATION = 0;

    @Id
    private IngredientID id;

    @Setter
    private String name;

    @Setter
    private Unit unit;

    @Setter
    private Integer minimumStockValue;

    @Setter
    private IngredientStatus ingredientStatus;

    @Setter
    private Integer ingredientStock;

    @JsonCreator
    public Ingredient(@NotBlank String name, @NotNull Unit unit) {
        this.id = Identificator.newInstance(IngredientID.class);
        this.name = name;
        this.unit = unit;
        this.minimumStockValue = MINIMUM_STOCK_VALUE_ON_CREATION;
        this.ingredientStatus = IngredientStatus.IN_TEST;
        this.ingredientStock = INGREDIENT_STOCK_ON_CREATION;
    }

    public Ingredient(@NotNull IngredientID id, @NotBlank String name, @NotNull Unit unit) {
        this.id = id;
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

    // TODO criar método para verificar o minimumStockValue contra o ingredientStock
}
