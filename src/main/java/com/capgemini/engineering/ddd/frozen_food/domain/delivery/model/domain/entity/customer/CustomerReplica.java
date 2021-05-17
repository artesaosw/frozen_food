package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.customer;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids.CustomerID;
import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class CustomerReplica implements AggregateRoot, Serializable {

    @Id @Getter @Setter
    private CustomerID customerID;

    @Getter @Setter @NonNull
    private String name;

    @Getter @Setter @NonNull
    private NIF nif;

    @Getter @Setter @Email(message = "Email invalid!" )
    private String email;

    @Getter @Setter  @Pattern(regexp="[9][0-9]{8}",message = "Invalid cellphone number!")
    private String cellphoneNumber;

    @Getter @Setter @NonNull
    private Address address;

    public CustomerReplica(CustomerReplica customerReplica){
        this.customerID = customerReplica.getCustomerID();
        this.name = customerReplica.getName();
        this.nif = customerReplica.getNif();
        this.email = customerReplica.getEmail();
        this.cellphoneNumber = customerReplica.getCellphoneNumber();
        this.address = customerReplica.getAddress();
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
