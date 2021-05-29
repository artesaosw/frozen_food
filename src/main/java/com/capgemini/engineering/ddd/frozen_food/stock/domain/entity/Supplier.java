package com.capgemini.engineering.ddd.frozen_food.stock.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.NIF;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.SupplierID;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Validated
@Document(collection = "supplier_stock")
public class Supplier implements AggregateRoot, Serializable {

    @Id
    private SupplierID id;

    @Setter
    private String name;

    @Setter
    private NIF nif;

    @Setter
    @Email(message = "Email invalid!")
    private String email;

    @Pattern(regexp = "[9][0-9]{8}", message = "Invalid cellphone number!")
    @Setter
    private String cellPhone;

    @JsonCreator
    public Supplier(@NotBlank String name, @NotNull NIF nif, @NotBlank String email, @NotBlank String cellPhone) {
        this.id = Identificator.newInstance(SupplierID.class);
        this.name = name;
        this.nif = nif;
        this.email = email;
        this.cellPhone = cellPhone;
    }

    public SupplierID id() {
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
