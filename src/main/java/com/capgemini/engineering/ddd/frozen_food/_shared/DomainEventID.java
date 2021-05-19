package com.capgemini.engineering.ddd.frozen_food._shared;


import javax.validation.constraints.NotNull;
import java.util.UUID;

public class DomainEventID implements Identificator {

    private UUID id;

    DomainEventID(@NotNull UUID id) {
        this.id = id;
    }

    public DomainEventID() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID id() {
        return this.id;
    }
}
