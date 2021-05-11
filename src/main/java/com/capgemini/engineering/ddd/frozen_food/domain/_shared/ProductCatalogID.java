package com.capgemini.engineering.ddd.frozen_food.domain._shared;

import java.io.Serializable;
import java.util.UUID;

public class ProductCatalogID implements Identificator, Serializable {

    private UUID id;

    @Override
    public UUID id() {
        return null;
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
