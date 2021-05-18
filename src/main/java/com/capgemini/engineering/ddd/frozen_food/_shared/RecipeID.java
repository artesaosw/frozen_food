package com.capgemini.engineering.ddd.frozen_food._shared;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class RecipeID implements Identificator, Serializable {

    private UUID id;

    RecipeID(@NotNull UUID id) {
        this.id = id;
    }

    public RecipeID() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID id() {
        return id;
    }

}
