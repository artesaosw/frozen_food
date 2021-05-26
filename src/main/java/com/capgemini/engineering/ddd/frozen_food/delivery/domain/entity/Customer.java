package com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.Util;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.Address;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.NIF;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.CustomerID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Document(collection = "customer_delivery")
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements AggregateRoot, Serializable {

    @Id
    @Getter @Setter
    private CustomerID customerID;

    @Getter @Setter @Size(min = 3, message = Util.NAME_VALIDATION)
    private String name;

    @Getter @Setter @NotBlank(message = Util.NIF_VALIDATION)
    private NIF nif;

    @Getter @Setter @Email(message = Util.EMAIL_VALIDATION)
    private String email;

    @Getter @Setter  @Pattern(regexp= Util.CELLPHONE_REGEX,message = Util.PHONE_VALIDATION)
    private String cellphoneNumber;

    @Getter @Setter @NotBlank
    private Address address;

    public Customer(Customer customer){
        this.customerID = customer.getCustomerID();
        this.name = customer.getName();
        this.nif = customer.getNif();
        this.email = customer.getEmail();
        this.cellphoneNumber = customer.getCellphoneNumber();
        this.address = customer.getAddress();
    }

    @Override
    public Identificator id() {
        return this.customerID;
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
