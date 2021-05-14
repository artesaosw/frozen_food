package com.capgemini.engineering.ddd.frozen_food.domain._shared;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class ProductionOrderID implements Identificator, Serializable {

    private UUID id;

    ProductionOrderID(@NotNull UUID id) {
        this.id = id;
    }

    public ProductionOrderID() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID id() {
        return id;
    }
}
