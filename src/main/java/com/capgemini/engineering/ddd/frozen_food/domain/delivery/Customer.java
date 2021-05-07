package com.capgemini.engineering.ddd.frozen_food.domain.delivery;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import lombok.AccessLevel;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class Customer implements AggregateRoot, Serializable {

    private CustomerID id;

    @Setter(AccessLevel.PROTECTED)
    private String name;

    @Setter(AccessLevel.PROTECTED)
    private String address;

    @Setter(AccessLevel.PROTECTED)
    private String billingInfo;

    public Customer() {

    }

    public Customer(@NotBlank String name, @NotBlank String address, @NotBlank String billingInfo) {
        this.id = Identificator.newInstance(CustomerID.class);
        this.name = name;
        this.address = address;
        this.billingInfo = billingInfo;
    }

    @Override
    public Identificator id() {
        return this.id;
    }

    @Override
    public boolean isEqualsTo(Object other) {
        return AggregateRoot.super.isEqualsTo(other);
    }

    @Override
    public int hashcode() {
        return AggregateRoot.super.hashcode();
    }
}
