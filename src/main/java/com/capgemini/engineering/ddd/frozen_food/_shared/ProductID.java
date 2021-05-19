package com.capgemini.engineering.ddd.frozen_food._shared;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class ProductID implements Identificator, Serializable {

    private UUID id;

    ProductID(@NotNull UUID id){
        this.id = id;
    }

    public ProductID() {
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
        return this.id;
    }

    @Override
    public boolean isEqualsTo(Object other) {
        return Identificator.super.isEqualsTo(other);
    }

    @Override
    public int hashcode() {
        return Identificator.super.hashcode();
    }

    @Override
    public String toString() {
        return id + "";
    }
}
