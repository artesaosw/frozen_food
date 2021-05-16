package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@NoArgsConstructor
public class Supplier implements AggregateRoot, Serializable {

    @BsonProperty(value = "_id")
    private SupplierID id;

    private String name;

    private NIF nif;

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
