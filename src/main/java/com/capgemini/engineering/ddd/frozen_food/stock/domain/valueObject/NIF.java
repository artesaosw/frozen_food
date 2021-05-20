package com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject;

import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.InvalidElementException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Getter
public class NIF {

    @NotBlank
    @Pattern(regexp = "[0-9]{9}", message = "NIF must have 9 digits.")
    String nif;

    @JsonCreator
    public NIF(@NotBlank @Pattern(regexp = "[0-9]{9}", message = "NIF must have 9 digits.") String nif) {
        setNif(nif);
    }

    public void setNif(@NotBlank String nif) {
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
        if (checkControlDigit == 0 || checkControlDigit == 1) {
            return controlDigit == 0;
        } else {
            return (11 - checkControlDigit) == controlDigit;
        }
    }

    @Override
    public String toString() {
        return this.nif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        NIF nif1 = (NIF) o;
        return Objects.equals(nif, nif1.nif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif);
    }
}
