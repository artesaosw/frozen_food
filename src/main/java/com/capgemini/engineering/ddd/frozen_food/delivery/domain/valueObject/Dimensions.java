package com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.util.Objects;

@AllArgsConstructor
public class Dimensions{

    @Getter @Setter
    private Units weightUnit;

    @Getter @Setter @Positive
    private double weight;

    @Getter @Setter
    private Units volumeUnit;

    @Getter @Setter @Positive
    private double volume;

    @Getter @Setter
    private Units lengthUnit;

    @Getter @Setter @Positive
    private double height;

    @Getter @Setter @Positive
    private double width;


    @Override
    public boolean equals(Object objectDimensions){
        if(this == objectDimensions){
            return true;
        }
        if(objectDimensions == null || getClass() != objectDimensions.getClass()){
            return false;
        }
        Dimensions dimensions = (Dimensions) objectDimensions;
        return Objects.equals(this.weightUnit, dimensions.getLengthUnit()) &&
                this.weight == dimensions.getWeight() &&
                Objects.equals(this.volumeUnit,dimensions.getVolumeUnit()) &&
                this.volume == dimensions.getVolume() &&
                Objects.equals(this.lengthUnit, dimensions.getLengthUnit()) &&
                this.height == dimensions.getHeight() &&
                this.width == dimensions.getWidth();
    }

    @Override
    public int hashCode(){
        return Objects.hash(weightUnit,weight,volumeUnit,volume,lengthUnit,height,width);
    }

    @Override
    public String toString(){
        return String.format("Weight: %.2f %s %nVolume: %.2f %s %nHeight: %.2f %s %nWidth: %.2f %s",
                weight,weightUnit,volume,volumeUnit,height,lengthUnit,width,lengthUnit);
    }
}
