package com.capgemini.engineering.ddd.frozen_food.domain._shared;

import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.UUID;

public class DemandaID implements Identificator, Serializable {

    private UUID id;

    DemandaID(@NotNull UUID id){
        this.id = id;
    }

    public DemandaID() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID id() {
        return id;
    }
}
