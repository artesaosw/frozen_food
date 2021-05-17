package com.capgemini.engineering.ddd.frozen_food.domain.delivery.external;

 import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

 import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO {

    private UUID customerID;

    private String name;

    private String nif;

    private String email;

    private String cellphoneNumber;

    private AddressDTO addressDTO;
}
