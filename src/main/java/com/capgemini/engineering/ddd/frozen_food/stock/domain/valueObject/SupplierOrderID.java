package com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject;

import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
public class SupplierOrderID implements Identificator, Serializable {

    private UUID id;

    SupplierOrderID(@NotNull UUID id) {
        this.id = id;
    }

    public SupplierOrderID() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID id() {
        return id;
    }
}
