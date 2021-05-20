package com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject;

import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
public class SupplierID implements Identificator, Serializable {

    private UUID id;

    SupplierID(@NotNull UUID id) {
        this.id = id;
    }

    public SupplierID() {
        this.id = UUID.randomUUID();
    }

    public SupplierID(String sid) {
        this.id = Identificator.newInstance(SupplierID.class, sid);
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
