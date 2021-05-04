package com.capgemini.engineering.ddd.frozen_food.domain.stock.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.exception.InvalidElementException;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
public class Supplier implements AggregateRoot, Serializable {

    private static final String NIF_REGEX = "[1-9]{1}[0-9]{8}";

    private SupplierID id;

    private String name;

    private Integer nif;

    protected Supplier() {
    }

    public Supplier(@NotBlank String name, @NotNull Integer nif) {
        this.id = Identificator.newInstance(SupplierID.class);
        this.name = name;
        setNif(nif);
    }

    public SupplierID id() {
        return this.id;
    }

    private void setNif(Integer nif) {
        if (verifyRegex(String.valueOf(nif), NIF_REGEX)) {
            this.nif = nif;
        } else {
            throw new InvalidElementException("NIF must have 9 digits.");
        }
    }

    private static boolean verifyRegex(String regex, String validRegex) {
        return regex.matches(validRegex);
    }
}
