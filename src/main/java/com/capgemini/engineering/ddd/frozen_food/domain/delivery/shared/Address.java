package com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared;

import com.capgemini.engineering.ddd.frozen_food.domain.delivery.domain.exception.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;

public class Address implements Serializable {

    @Getter @Setter
    private String street;
    @Getter @Setter
    private String doorNumber;
    @Getter @NonNull
    private String postalCode;
    @Getter @Setter
    private String addressNote;

    public Address(@NonNull String street, @NonNull String doorNumber, @NonNull String postalCode, String addressNote){
        this.street = street;
        this.doorNumber = doorNumber;
        this.addressNote = addressNote;
        setPostalCode(postalCode);
    }

    public void setPostalCode(String postalCode){
        if(isPCValid(postalCode)){
            this.postalCode = postalCode;
        }
        else{
            throw new PostalCodeInvalidException("Postal Code invalid!");
        }
    }

    public static boolean isPCValid(String postalCode){
        char [] pc = postalCode.toCharArray();
        boolean isValidP1 = false;
        boolean isValidP2 = false;
        if(postalCode.length() == 8 && (pc[4] == '-')){
            for(int i = 0; i < 4; i++){
                if(Character.isDigit(pc[i])){
                    isValidP1 = true;
                }
            }
            for(int i = 5; i < 8; i++){
                if(Character.isDigit(pc[i])){
                    isValidP2 = true;
                }
            }
        }
        return (isValidP1 && isValidP2);
    }
}
