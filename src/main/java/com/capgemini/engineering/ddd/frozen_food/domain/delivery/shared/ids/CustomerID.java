package com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.ids;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.UUID;

public class CustomerID implements Identificator, Serializable {

    private UUID id;

    CustomerID(@NotNull UUID id){
        this.id = id;
    }

    public CustomerID() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID id() {
        return id;
    }
}
