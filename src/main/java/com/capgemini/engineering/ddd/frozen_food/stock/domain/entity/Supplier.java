package com.capgemini.engineering.ddd.frozen_food.stock.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.NIF;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.SupplierID;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Document(collection = "supplier_stock")
public class Supplier implements AggregateRoot, Serializable {

    @Id
    private SupplierID id;

    private String name;

    private NIF nif;

    @JsonCreator
    public Supplier(@NotBlank String name, @NotNull NIF nif) {
        this.id = Identificator.newInstance(SupplierID.class);
        this.name = name;
        this.nif = nif;
    }

    public SupplierID id() {
        return this.id;
    }

    @Override
    public boolean isEqualsTo(Object other) {
        return AggregateRoot.super.isEqualsTo(other);
    }

    @Override
    public int hashcode() {
        return AggregateRoot.super.hashcode();
    }
}
