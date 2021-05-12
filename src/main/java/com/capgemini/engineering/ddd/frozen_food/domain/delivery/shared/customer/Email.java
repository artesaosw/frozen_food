/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.customer;

/**
 *
 * @author CAD
 */

import com.capgemini.engineering.ddd.frozen_food.domain.delivery.domain.exception.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class Email {
   
    /**
     * The email
     */
    @Getter
    private String email;

    /**
     * Email constructor
     * @param email 
     */
    public Email(Email email){
        setEmail(email.email);
    }

     /**
      * Sets the email
      * @param email 
      */
    public void setEmail(String email){
        if(isEmailValid(email)){
            this.email = email;
        }
        else{
            throw new EmailInvalidException("The email is invalid!");
        }
    }

    /**
     * Validates the email
     * @param email
     * @return validation status
     */
    public static boolean isEmailValid(String email) {
        boolean isValid = false;
        if (email != null && email.length() > 0) {
            String expressao = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expressao, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isValid = true;
            }
        }
        return isValid;
    }

    @Override
    public String toString() {
        return email;
    }
}


