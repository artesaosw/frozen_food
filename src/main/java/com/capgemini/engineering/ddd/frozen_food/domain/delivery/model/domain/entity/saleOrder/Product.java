package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.saleOrder;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.BatchID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.Units;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids.ProductID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.RecipeID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Positive;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class Product implements AggregateRoot, Serializable {

    @Id @Getter @Setter
    private ProductID productID;

    @Getter @Setter @Positive
    private int quantity;

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
