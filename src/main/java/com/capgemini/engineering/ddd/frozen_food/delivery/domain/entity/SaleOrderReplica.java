package com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.SaleOrderID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public class SaleOrderReplica implements AggregateRoot, Serializable {

    @Id @Getter @Setter
    private SaleOrderID saleOrderID;

    @Getter @Setter
    private CustomerID customerID;

    @Getter @Setter
    private Map<ProductReplica, Integer> saleOrderProductsList;

    @Getter @Setter
    private Date saleOrderDate;

    @Override
    public Identificator id() {
        return this.saleOrderID;
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
