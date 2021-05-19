package com.capgemini.engineering.ddd.frozen_food._shared;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class OrderID implements Identificator, Serializable {

    private UUID id;

    OrderID(@NotNull UUID id) {
        this.id = id;
    }

    public OrderID() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID id() {
        return id;
    }
}
