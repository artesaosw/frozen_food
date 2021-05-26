package com.capgemini.engineering.ddd.frozen_food.sales.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.CustomerID;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.valueObject.Address;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.valueObject.NIF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

@Document(collection = "customer_sales")
public class Customer implements AggregateRoot, Serializable {

    @Id
    private String id;

    private CustomerID customerID;

    @NotBlank
    private String name;

    @NotNull
    private NIF nif;

    @NotBlank
    @Email(message = "Email invalid!" )
    //@Indexed(unique=true)
    private String email;

    @NotBlank
    @Pattern(regexp="[9][0-9]{8}",message = "Invalid cellphone number!")
    private String cellphoneNumber;

    @NotNull
    private Address address;

    private boolean activated = true;

    public Customer() {

    }

//    public Customer(String name, NIF nif, String email, String cellphoneNumber, Address address) {
//        this.customerID = Identificator.newInstance(CustomerID.class);
//        this.name = name;
//        this.nif = nif;
//        this.address = address;
//        this.cellphoneNumber = cellphoneNumber;
//    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return activated == customer.activated &&
                Objects.equals(id, customer.id) &&
                Objects.equals(customerID, customer.customerID) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(nif, customer.nif) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(cellphoneNumber, customer.cellphoneNumber) &&
                Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerID, name, nif, email, cellphoneNumber, address, activated);
    }
}
