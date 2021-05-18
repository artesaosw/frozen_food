package com.capgemini.engineering.ddd.frozen_food.stock.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.InvalidElementException;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class NIF {

    @Pattern(regexp = "[0-9]{9}", message = "NIF must have 9 digits.")
    String nif;

    public NIF(@NotBlank String nif) {
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
}
