package com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.Units;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.ids.ProductID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Positive;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class ProductReplica implements AggregateRoot, Serializable {

    @Id @Getter @Setter
    private ProductID productID;

    @Getter @Setter
    private Units weightUnit;

    @Getter @Setter @Positive
    private float weight;

    @Getter @Setter
    private Units volumeUnit;

    @Getter @Setter @Positive
    private float volume;

    @Getter @Setter
    private Units lengthUnit;

    @Getter @Setter @Positive
    private float height;

    @Getter @Setter @Positive
    private float width;

    @Override
    public Identificator id() {
        return this.productID;
    }

    @Override
    public int hashCode() {
        return AggregateRoot.super.hashcode();
    }

    @Override
    public boolean equals(Object obj) {
        return AggregateRoot.super.isEqualsTo(obj);
    }
}
