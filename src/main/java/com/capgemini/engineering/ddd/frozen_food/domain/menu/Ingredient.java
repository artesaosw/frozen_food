package com.capgemini.engineering.ddd.frozen_food.domain.menu;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.IngredientID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
public class Ingredient implements AggregateRoot, RecipeItem , Serializable {

    private IngredientID id;

    @Setter(AccessLevel.PROTECTED)
    private String description;

    //Just to support ORM frameworks
    protected Ingredient() {}

    public Ingredient(@NotBlank String description) {
        this.id = Identificator.newInstance(IngredientID.class);
        this.description = description;
    }

    @Override
    public Identificator id() {
        return this.id;
    }

    @Override
    public int hashCode() {
        return AggregateRoot.super.hashcode();
    }

    @Override
    public boolean equals(Object obj) {
        return AggregateRoot.super.isEqualsTo(obj);
    }

    @Override
    public String title() {
        return getDescription();
    }
}
