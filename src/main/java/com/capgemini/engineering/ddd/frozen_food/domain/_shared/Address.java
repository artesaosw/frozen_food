package com.capgemini.engineering.ddd.frozen_food.domain._shared;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
public class Address implements Serializable {

    @Setter
    private String street;
    @Setter
    private String doorNumber;
    @Setter
    private String postalCode;

    public Address(@NotBlank String street, @NotBlank String doorNumber, @NotBlank String postalCode){
        this.street = street;
        this.doorNumber = doorNumber;
        this.postalCode = postalCode;
    }
}
