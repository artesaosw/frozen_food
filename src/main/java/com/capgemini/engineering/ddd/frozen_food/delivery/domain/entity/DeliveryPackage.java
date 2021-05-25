package com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.*;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.OrderDimensions;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.DeliveryPackageID;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.RouteID;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.SaleOrderID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class DeliveryPackage implements AggregateRoot, Serializable {

    @Id @Getter
    private DeliveryPackageID deliveryPackageID;

    @Getter
    private SaleOrderID saleOrderID;

    @Getter
    private OrderDimensions orderDimensions;

    @Getter @Setter
    private OrderStatus orderStatus;

    @Getter @Setter
    private RouteID routeID;

    public DeliveryPackage(DeliveryPackageID deliveryPackageID, SaleOrderID saleOrderID, OrderDimensions orderDimensions,
                           OrderStatus orderStatus){
        this.deliveryPackageID = Identificator.newInstance(DeliveryPackageID.class);
        this.saleOrderID = saleOrderID;
        this.orderStatus = OrderStatus.PACKAGE_PREPARATION;
    }

    public void updatePackagingStatus(){

    }

    @Override
    public Identificator id() {
        return this.deliveryPackageID;
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
