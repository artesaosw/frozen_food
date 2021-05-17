package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.converter;

import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.Units;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.exception.InvalidElementException;

public class UnitsConverter {

    public Units whichUnit(String unit){
        switch (unit){
            case "KG":
                return Units.KG;
                break;
            case "G":
                return Units.G;
                break;
            case "MG":
                return Units.MG;
                break;
            case "L":
                return Units.L;
                break;
            case "ML":
                return Units.ML;
                break;
            case "M":
                return Units.M;
                break;
            case "CM":
                return Units.CM;
                break;
            default:
                throw new InvalidElementException("Invalid measure unit!");
        }
    }
}
