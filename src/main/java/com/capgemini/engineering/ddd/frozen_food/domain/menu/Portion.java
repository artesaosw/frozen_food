package com.capgemini.engineering.ddd.frozen_food.domain.menu;

import com.capgemini.engineering.ddd.frozen_food.__metadata.ValueObject;
import com.capgemini.engineering.ddd.frozen_food._shared.Unit;
<<<<<<< HEAD
import com.sun.istack.NotNull;
=======
>>>>>>> ba701e60c2a57217fb327865c8ea317b4224b950
import lombok.Getter;

import javax.validation.constraints.Positive;
import java.io.Serializable;

@Getter
public class Portion implements ValueObject, Serializable {

    private RecipeItem item;

    private Unit unit;

    private double amount;

    public Portion(@NotNull RecipeItem item, @NotNull Unit unit, @Positive double amount) {
        this.item = item;
        this.unit = unit;
        this.amount = amount;
    }
}
