package com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids;

import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;

import javax.validation.constraints.NotNull;
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
