package com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.Dimensions;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.ProductID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Document(collection = "product_delivery")
@AllArgsConstructor
@NoArgsConstructor
public class Product implements AggregateRoot, Serializable {

    @Id @Getter @Setter
    private ProductID productID;

    @Getter @Setter @Size(min = 3)
    private String productName;

    @Getter @Setter @NotBlank
    private Dimensions dimensions;

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
