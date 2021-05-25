package com.capgemini.engineering.ddd.frozen_food.delivery.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private String street;

    private String doorNumber;

    private String postalCode;

    private String addressNote;
}
