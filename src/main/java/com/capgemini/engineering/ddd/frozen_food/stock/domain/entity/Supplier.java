package com.capgemini.engineering.ddd.frozen_food.stock.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.SupplierID;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Supplier implements AggregateRoot, Serializable {

    @Id
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
