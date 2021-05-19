package com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids;

import com.capgemini.engineering.ddd.frozen_food._shared.Identificator;
import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.UUID;

public class ProductID implements Identificator, Serializable {

    private UUID id;

    public ProductID(@NotNull UUID id){
        this.id = id;
    }

    public ProductID() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID id() {
        return id;
    }
}
