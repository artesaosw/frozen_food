package com.capgemini.engineering.ddd.frozen_food.stock.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.NIF;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.SupplierID;
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
    String id;

    private SupplierID supplierID;

    @NotBlank
    private String name;

    @NotNull
    private NIF nif;

    public Supplier(@NotBlank String name, @NotNull NIF nif) {
        this.supplierID = Identificator.newInstance(SupplierID.class);
        this.name = name;
        this.nif = nif;
    }

    public SupplierID id() {
        return this.supplierID;
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
