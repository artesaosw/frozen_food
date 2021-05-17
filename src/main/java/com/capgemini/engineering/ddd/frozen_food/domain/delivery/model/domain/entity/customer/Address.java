package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.customer;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@AllArgsConstructor
public class Address implements AggregateRoot, Serializable {

    @Getter @Setter @Size(min=3)
    private String street;
    @Getter @Setter @NonNull
    private String doorNumber;
    @Getter @Setter @Pattern(regexp="[1-9][0-9]{3}-[0-9]{3}",message = "Invalid Postal Code!")
    private String postalCode;
    @Getter @Setter
    private String addressNote;

    @Override
    public Identificator id() {
        return null;
    }

    @Override
    public int hashCode() {
        return AggregateRoot.super.hashcode();
    }

    @Override
    public boolean equals(Object obj) {
        return AggregateRoot.super.isEqualsTo(obj);
    }
}
