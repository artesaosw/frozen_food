package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.customer;


import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.exception.InvalidElementException;
import lombok.Getter;
import lombok.NonNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author JorgePires
 */

@Getter
public class NIF implements AggregateRoot, Serializable {

    @Pattern(regexp = "[0-9]{9}", message = "NIF must have 9 digits.")
    private String nif;

    public NIF(@NonNull String nif) {
        setNif(nif);
    }

    private void setNif(String nif) {
        if (nifValidation(nif)) {
            this.nif = nif;
        } else {
            throw new InvalidElementException("NIF not valid.");
        }
    }

    private boolean nifValidation(String nif) {
        int controlDigit = Character.getNumericValue(nif.charAt(nif.length() - 1));
        int sum = 0;
        for (int i = 0; i < nif.length() - 1; i++) {
            sum += Character.getNumericValue(nif.charAt(i)) * (nif.length() - i);
        }
        int checkControlDigit = sum % 11;
        if (checkControlDigit == 0 | checkControlDigit == 1) {
            return controlDigit == 0;
        } else {
            return (11 - checkControlDigit) == controlDigit;
        }
    }

    @Override
    public Identificator id() {
        return null;
    }

    @Override
    public int hashCode() {
        return AggregateRoot.super.hashcode();
    }

    @Override
    public boolean equals(Object obj) {
        return AggregateRoot.super.isEqualsTo(obj);
    }

}
