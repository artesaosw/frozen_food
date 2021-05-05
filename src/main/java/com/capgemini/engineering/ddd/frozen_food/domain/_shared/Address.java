package com.capgemini.engineering.ddd.frozen_food.domain._shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
public class Address implements Serializable {

    @Getter @Setter
    private String street;
    @Getter @Setter
    private String doorNumber;
    @Getter @Setter
    private PostalCode postalCode;

}
