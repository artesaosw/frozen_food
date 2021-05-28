package com.capgemini.engineering.ddd.frozen_food._shared.id;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class OrderID implements Identificator {

    private UUID id;

    OrderID(@NotNull UUID id) {
        this.id = id;
    }

    public OrderID() {
        this.id = UUID.randomUUID();
    }

    public OrderID(String id) {
        this.id = UUID.fromString(id);
    }

    @Override
    public UUID id() {
        return this.id;
    }

    @Override
    public String toString() {
        return id + "";
    }
}