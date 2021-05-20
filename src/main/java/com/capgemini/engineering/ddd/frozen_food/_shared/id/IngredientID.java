package com.capgemini.engineering.ddd.frozen_food._shared.id;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
public class IngredientID implements Identificator, Serializable {

    private UUID id;

    IngredientID(@NotNull UUID id) {
        this.id = id;
    }

    public IngredientID() {
        this.id = UUID.randomUUID();
    }

    public IngredientID(String id) {
        this.id = UUID.fromString(id);
    }

    @Override
    public UUID id() {
        return id;
    }

    @Override
    public String toString() {
        return id + "";
    }
}
