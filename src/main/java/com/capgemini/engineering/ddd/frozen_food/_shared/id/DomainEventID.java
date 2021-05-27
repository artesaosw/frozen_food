package com.capgemini.engineering.ddd.frozen_food._shared.id;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class DomainEventID implements Identificator {

    private UUID id;

    DomainEventID(@NotNull UUID id) {
        this.id = id;
    }

    public DomainEventID() {
        this.id = UUID.randomUUID();
    }

    public DomainEventID(String id) {
        this.id = UUID.fromString(id);
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
