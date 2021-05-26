package com.capgemini.engineering.ddd.frozen_food.production.valueObject;

import com.capgemini.engineering.ddd.frozen_food.__metadata.ValueObject;
import com.capgemini.engineering.ddd.frozen_food._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food.production.entity.RecipeItem;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
public class Portion implements ValueObject, Serializable {

    private RecipeItem item;

    private Unit unit;

    private double amount;

    public Portion(@NotNull Unit unit, @Positive double amount) {
        this.unit = unit;
        this.amount = amount;
    }
}
