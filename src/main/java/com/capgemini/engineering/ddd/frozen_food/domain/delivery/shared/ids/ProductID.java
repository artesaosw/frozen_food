package com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.ids;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.UUID;

public class ProductID implements Identificator, Serializable {

    private UUID id;

    ProductID(@NotNull UUID id){
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
