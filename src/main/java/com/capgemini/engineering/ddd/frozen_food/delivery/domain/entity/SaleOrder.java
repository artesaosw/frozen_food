package com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.OrderTrackingInfo;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.ProductID;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.SaleOrderID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
public class SaleOrder implements AggregateRoot, Serializable {

    @Id @Getter @Setter
    private SaleOrderID saleOrderID;

    @Getter @Setter
    private CustomerID customerID;

    @Getter @Setter
    private Map<ProductID, Integer> saleOrderProductsList;

    @Getter @Setter
    private LocalDateTime saleOrderPlacedDate;

    @Getter @Setter @NotBlank
    private OrderStatus orderStatus;

    @Getter @Setter
    private OrderTrackingInfo orderTrackingInfo;

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
