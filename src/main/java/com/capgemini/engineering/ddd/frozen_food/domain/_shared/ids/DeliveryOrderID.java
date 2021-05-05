package com.capgemini.engineering.ddd.frozen_food.domain._shared.ids;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.UUID;

public class DeliveryOrderID  implements Identificator, Serializable {

    private UUID id;

    DeliveryOrderID(@NotNull UUID id){
        this.id = id;
    }

    public DeliveryOrderID() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID id() {
        return id;
    }

}
