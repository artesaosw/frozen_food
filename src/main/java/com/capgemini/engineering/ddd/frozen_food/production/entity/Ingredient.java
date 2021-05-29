package com.capgemini.engineering.ddd.frozen_food.production.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

//Class que recebe Ingrediente do Menu e Suporta o Handler
@Data
@Document(collection= "ingredient_producao")
public class Ingredient implements AggregateRoot, Serializable, RecipeItem {

    @Id
    private IngredientID id;

    private String description;

    private Unit unit;

    //Just to support ORM frameworks
    protected Ingredient() {}

    public Ingredient(@NotBlank IngredientID id, @NotBlank String description,@NotNull Unit unit) {
        this.id = Identificator.newInstance(IngredientID.class);
        this.description = description;
        this.unit = unit;
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

    @Override
    public Identificator id() {
        return this.id;
    }
}