package com.capgemini.engineering.ddd.frozen_food._shared;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class DomainEventID implements Identificator {

    private UUID id;

    DomainEventID(@NotNull UUID id){
        this.id = id;
    }

    public DomainEventID() {
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
    public String toString() {
        return id + "";
    }
}
