package com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.customer;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.ids.CustomerID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
public class Customer implements AggregateRoot, Serializable {

    @Id @Getter
    private CustomerID customerID;

    @Getter @Setter @NonNull
    private String name;

    @Getter @Setter @NonNull
    private NIF nif;

    private String email;

    @Getter @Setter @NonNull
    private Address address;

    @Getter @Setter
    private PaymentMethod paymentMethod;

    @Override
    public Identificator id() {
        return null;
    }
}
