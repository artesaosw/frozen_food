package com.capgemini.engineering.ddd.frozen_food.delivery.external;

import lombok.*;

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
