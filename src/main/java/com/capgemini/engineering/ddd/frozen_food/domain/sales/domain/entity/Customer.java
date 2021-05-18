package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.customerInfo.Address;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.customerInfo.NIF;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class Customer implements AggregateRoot, Serializable {

    @Id
    private String id;

    private CustomerID customerID = Identificator.newInstance(CustomerID.class);

    @NotBlank
    private String name;

    @NotNull
    private NIF nif;

    @Email(message = "Email invalid!" )
    private String email;

    @Pattern(regexp="[9][0-9]{8}",message = "Invalid cellphone number!")
    @NotBlank
    private String cellphoneNumber;

    @NotNull
    private Address address;

    private boolean activated = true;

    public Customer() {

    }

    public Customer(String name, NIF nif, String email, String cellphoneNumber, Address address) {
        this.customerID = Identificator.newInstance(CustomerID.class);
        this.name = name;
        this.nif = nif;
        this.address = address;
        this.cellphoneNumber = cellphoneNumber;
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

    public NIF getNif() {
        return nif;
    }

    public void setNif(NIF nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
