package com.capgemini.engineering.ddd.frozen_food._shared.id;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
public class ChefOrderID implements Identificator, Serializable {

    private UUID id;

    ChefOrderID(@NotNull UUID id) {
        this.id = id;
    }

    public ChefOrderID() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID id() {
        return id;
    }
}
