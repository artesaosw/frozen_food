package com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery;

import com.capgemini.engineering.ddd.frozen_food._shared.id.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.valueObject.Address;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CustomerDTO {

    @NotNull
    private CustomerID customerID;

    @NotBlank
    private String name;

    @NotBlank
    @Email(message = "Email invalid!" )
    private String email;

    @NotBlank
    @Pattern(regexp="[9][0-9]{8}",message = "Invalid cellphone number!")
    private String cellphoneNumber;

    @NotNull
    private Address address;

    public CustomerDTO() {

    }

    public CustomerDTO(CustomerID customerID, String name, String email, String cellphoneNumber, Address address) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.cellphoneNumber = cellphoneNumber;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
