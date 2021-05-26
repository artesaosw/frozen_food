package com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject;

import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Getter @Setter @Size(min=3)
    private String street;
    @Getter @Setter @NonNull
    private String doorNumber;
    @Getter @Setter @Pattern(regexp="[1-9][0-9]{3}-[0-9]{3}",message = "Invalid Postal Code!")
    private String postalCode;
    @Getter @Setter
    private String addressNote;


    @Override
    public int hashCode() {

        return Objects.hash(street,doorNumber,postalCode,addressNote);
    }

    @Override
    public boolean equals(Object objectAddress){
        if(this == objectAddress){
            return true;
        }
        if( objectAddress == null || getClass() !=  objectAddress.getClass()){
            return false;
        }
        Address address = (Address) objectAddress;
        return Objects.equals(this.street, address.getStreet()) &&
                Objects.equals(this.doorNumber,address.getDoorNumber()) &&
                Objects.equals(this.postalCode, address.getPostalCode());
    }

    @Override
    public String toString(){
        if(!addressNote.equals("")){
            return String.format("Street: %s %nDoor number: %s %nPostal Code: %s %nAddress note: %s",
                    street,doorNumber,postalCode, addressNote);
        }
        else{
            return String.format("Street: %s %nDoor number: %s %nPostal Code: %s",
                    street,doorNumber,postalCode);
        }
    }
}
