package com.capgemini.engineering.ddd.frozen_food._shared.dto.production_sales.valueObject;

import com.capgemini.engineering.ddd.frozen_food.__metadata.ValueObject;

import javax.validation.constraints.Positive;
import java.util.Objects;

public class Dimensions implements ValueObject {

//    //Sera necessario especificar o sistema de unidades (metrico, imperial, etc.) ?
//    private MeasurementSystem system;

    @Positive
    private double length;

    @Positive
    private double width;

    @Positive
    private double height;

    @Positive
    private double weight;

    public Dimensions() {

    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dimensions that = (Dimensions) o;
        return Double.compare(that.length, length) == 0 &&
                Double.compare(that.width, width) == 0 &&
                Double.compare(that.height, height) == 0 &&
                Double.compare(that.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width, height, weight);
    }
}
