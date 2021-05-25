package com.capgemini.engineering.ddd.frozen_food.delivery.domain.converter;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.exception.InvalidElementException;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.Units;

public class UnitsConverter {

    public Units whichUnit(String unit){
        Units value;
        switch (unit){
            case "KG":
                value = Units.KG;
                break;
            case "G":
                value = Units.G;
                break;
            case "MG":
                value = Units.MG;
                break;
            case "L":
                value = Units.L;
                break;
            case "ML":
                value = Units.ML;
                break;
            case "M":
                value = Units.M;
                break;
            case "CM":
                value = Units.CM;
                break;
            default:
                throw new InvalidElementException("Invalid measure unit!");
        }
        return value;
    }
}
