package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class Customer implements AggregateRoot, Serializable {

    @Id
    private String id;

    private CustomerID customerID = Identificator.newInstance(CustomerID.class);

    @NotBlank
    private String name;

    @NotBlank
    private String address;
    
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo(String billingInfo) {
        this.billingInfo = billingInfo;
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
