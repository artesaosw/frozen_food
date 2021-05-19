package com.capgemini.engineering.ddd.frozen_food.delivery.domain.ids;

import com.capgemini.engineering.ddd.frozen_food._shared.Identificator;
import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.UUID;

public class DeliveryPackageID implements Identificator, Serializable {

    private UUID id;

    DeliveryPackageID(@NotNull UUID id){
        this.id = id;
    }

    public DeliveryPackageID() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID id() {
        return id;
    }

}
