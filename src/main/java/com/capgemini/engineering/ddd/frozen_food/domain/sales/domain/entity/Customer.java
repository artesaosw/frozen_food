package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderID;
import lombok.AccessLevel;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Customer implements AggregateRoot, Serializable {

    @Id
    private String id;

    @NotNull
    private CustomerID customerID = Identificator.newInstance(CustomerID.class);

    @Setter(AccessLevel.PROTECTED)
    @NotBlank
    private String name;

    @Setter(AccessLevel.PROTECTED)
    @NotBlank
    private String address;

    @Setter(AccessLevel.PROTECTED)
    @NotBlank
    private String billingInfo;

    private boolean activated = true;

    public Customer() {

    }

    public Customer(@NotBlank String name, @NotBlank String address, @NotBlank String billingInfo) {
        this.customerID = Identificator.newInstance(CustomerID.class);
        this.name = name;
        this.address = address;
        this.billingInfo = billingInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomerID getCustomerID() {
        return customerID;
    }

    public void setCustomerID(CustomerID customerID) {
        this.customerID = customerID;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public Identificator id() {
        return this.customerID;
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
