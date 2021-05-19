package com.capgemini.engineering.ddd.frozen_food.delivery.domain.ids;

import com.capgemini.engineering.ddd.frozen_food._shared.Identificator;
import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.UUID;

public class SaleOrderID implements Identificator, Serializable {

    private UUID id;

    public SaleOrderID(@NotNull UUID id){
        this.id = id;
    }

    public SaleOrderID() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID id() {
        return id;
    }
}
