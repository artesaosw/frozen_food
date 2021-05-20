package com.capgemini.engineering.ddd.frozen_food._shared.id;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
public class ProductionOrderID implements Identificator, Serializable {

    private UUID id;

    ProductionOrderID(@NotNull UUID id) {
        this.id = id;
    }

    public ProductionOrderID() {
        this.id = UUID.randomUUID();
    }

    public ProductionOrderID(String id) {
        this.id = UUID.fromString(id);
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
