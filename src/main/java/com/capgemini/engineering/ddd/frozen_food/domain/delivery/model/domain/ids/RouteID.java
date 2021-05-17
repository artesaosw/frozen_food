package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.UUID;

public class RouteID implements Identificator, Serializable {

    private UUID id;

    RouteID(@NotNull UUID id){
        this.id = id;
    }

    public RouteID() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID id() {
        return id;
    }
}
