package com.capgemini.engineering.ddd.frozen_food._shared.id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class BatchID implements Identificator, Serializable {

    private UUID id;

    BatchID(@NotNull UUID id){
        this.id = id;
    }

    public BatchID() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID id() {
        return id;
    }
}