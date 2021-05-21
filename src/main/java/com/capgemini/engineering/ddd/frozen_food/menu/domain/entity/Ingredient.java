package com.capgemini.engineering.ddd.frozen_food.menu.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.IngredientID;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Document(collection = "ingredient_menu")
public class Ingredient implements AggregateRoot, RecipeItem , Serializable {

    @Id
    private IngredientID id;

    @Setter(AccessLevel.PROTECTED)
    private String description;

    //Just to support ORM frameworks
    protected Ingredient() {}

    @JsonCreator
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
