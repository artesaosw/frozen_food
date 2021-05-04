package com.capgemini.engineering.ddd.frozen_food.domain.stock.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.stock.exception.InvalidElementException;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class NIF {

    private static final String NIF_REGEX = "[0-9]{9}";

    String nif;

    public NIF(@NotEmpty String nif) {
        setNif(nif);
    }

    private void setNif(String nif) {
        if (nifValidation(nif)) {
            this.nif = nif;
        }
    }

    private boolean nifValidation(String nif) {
        if (!nif.matches(NIF_REGEX)) {
            throw new InvalidElementException("NIF must have 9 digits.");
        }
        int controlDigit = Character.getNumericValue(nif.charAt(8));
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