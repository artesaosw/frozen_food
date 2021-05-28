package com.capgemini.engineering.ddd.frozen_food.sales.domain.valueObject;

import com.capgemini.engineering.ddd.frozen_food.__metadata.ValueObject;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Address implements ValueObject {

    @Getter
    @Setter
    @Size(min=3)
    @NotBlank
    private String street;

    @Getter @Setter @NotBlank
    private String doorNumber;

    @NotBlank
    @Getter @Setter @Pattern(regexp="[1-9][0-9]{3}-[0-9]{3}",message = "Invalid Postal Code!")
    private String postalCode;

    @Getter @Setter
    private String addressNote;

    public Address() {

    }

    public Address(String street, String doorNumber, String postalCode, String addressNote) {
        this.street = street;
        this.doorNumber = doorNumber;
        this.postalCode = postalCode;
        this.addressNote = addressNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(doorNumber, address.doorNumber) &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(addressNote, address.addressNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, doorNumber, postalCode, addressNote);
    }
}
