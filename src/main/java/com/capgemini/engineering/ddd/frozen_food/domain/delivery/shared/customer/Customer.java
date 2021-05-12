package com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.customer;

import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.Address;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.ids.CustomerID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
public class Customer implements Serializable {

    @Id @Getter
    private CustomerID customerID;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private NIF nif;

    @Getter @Setter
    private Address address;

    @Getter @Setter
    private PaymentMethod paymentMethod;

}
