package com.capgemini.engineering.ddd.frozen_food.domain.menu;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.ValueObject;

import java.io.Serializable;

public class RecipeVersion implements ValueObject, Serializable {

    private static final short MAJOR_INITIAL = 0;

    private static final short MINOR_INITIAL = 1;

    private short major;

    private short minor;

    private RecipeVersion(){}

    private void checkOverflow(short number){
        if (number == Short.MAX_VALUE){
            throw new RecipeVersionOverflowException("Version number exceeds maximum alowed value.");
        }
    }

    void incrementMajor(){
        checkOverflow(major);
        major++;
    }

    void incrementMinor(){
        checkOverflow(minor);
        minor++;
    }

    public static RecipeVersion initial(){
        RecipeVersion rv = new RecipeVersion();
        rv.major = MAJOR_INITIAL;
        rv.minor = MINOR_INITIAL;
        return rv;
    }

}
