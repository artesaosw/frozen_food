package com.capgemini.engineering.ddd.frozen_food.domain._shared;

import com.sun.istack.NotNull;

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
}
