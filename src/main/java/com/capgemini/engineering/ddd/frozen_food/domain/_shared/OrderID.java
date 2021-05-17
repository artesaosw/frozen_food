package com.capgemini.engineering.ddd.frozen_food.domain._shared;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class OrderID implements Identificator, Serializable {

    private UUID id;

    public OrderID(@NotNull UUID id){
        this.id = id;
    }

    public OrderID() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public UUID id() {
        return id;
    }

    @Override
    public String toString() {
        return id + "";
    }
}
