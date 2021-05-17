package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.UUID;

public class TransportID implements Identificator, Serializable {

    private UUID id;

    TransportID(@NotNull UUID id){
        this.id = id;
    }

    public TransportID() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID id() {
        return id;
    }
}
