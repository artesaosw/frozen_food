package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.*;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.saleOrder.Product;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids.DeliveryPackageID;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids.SaleOrderID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    private DeliveryStatus deliveryStatus;

    @Getter @Setter
    private Date deliveryDate;

    public DeliveryPackage(DeliveryPackageID deliveryPackageID, SaleOrderID saleOrderID, Date saleOrderDate, CustomerID
            customerID, List<Product> productList, DeliveryStatus deliveryStatus){
        this.deliveryPackageID = Identificator.newInstance(DeliveryPackageID.class);
        this.saleOrderID = saleOrderID;

        this.deliveryStatus = deliveryStatus;
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
