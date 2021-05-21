package com.capgemini.engineering.ddd.frozen_food._shared;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class IngredientID implements Identificator, Serializable {

    private UUID id;

    IngredientID(@NotNull UUID id){
        this.id = id;
    }

    public IngredientID() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID id() {
        return id;
    }
}
