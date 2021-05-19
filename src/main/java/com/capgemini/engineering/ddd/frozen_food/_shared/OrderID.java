package com.capgemini.engineering.ddd.frozen_food._shared;

import com.sun.istack.NotNull;

import java.util.UUID;

public class OrderID implements Identificator {

    private UUID id;

    OrderID(@NotNull UUID id) {
        this.id = id;
    }

    public OrderID() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID id() {
        return this.id;
    }

}
