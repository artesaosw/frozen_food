package com.capgemini.engineering.ddd.frozen_food.domain.producao.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.IngredientID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

//Class que recebe Ingrediente do Menu e Suporta o Handler
@Getter
@Setter
public class Ingredient implements AggregateRoot, Serializable, RecipeItem {

    @Id
    private IngredientID id;

    private String description;

    //Just to support ORM frameworks
    protected Ingredient() {}

    public Ingredient(@NotBlank IngredientID id, @NotBlank String description) {
        this.id = Identificator.newInstance(IngredientID.class);;
        this.description = description;
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
        return null;
    }
}
