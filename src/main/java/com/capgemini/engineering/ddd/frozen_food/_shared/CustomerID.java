package com.capgemini.engineering.ddd.frozen_food._shared;

import javax.validation.constraints.NotNull;
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
