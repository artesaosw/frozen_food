package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.saleOrder;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids.SaleOrderID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class SaleOrderReplica implements AggregateRoot, Serializable {

    @Id @Getter @Setter
    private SaleOrderID saleOrderID;

    @Getter @Setter
    private CustomerID customerID;

    @Getter @Setter
    private List<Product> saleOrderProducts;

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
