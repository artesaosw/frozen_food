package com.capgemini.engineering.ddd.frozen_food.domain._shared.ids;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.UUID;

public class RecipeID implements Identificator, Serializable {

    private UUID id;

    RecipeID(@NotNull UUID id){
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
