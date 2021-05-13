package com.capgemini.engineering.ddd.frozen_food.domain.delivery.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.*;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.ids.CustomerID;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.ids.DeliveryPackageID;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.ids.SaleOrderID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class DeliveryPackage implements AggregateRoot {

    @Id @Getter
    private DeliveryPackageID deliveryPackageID;
    @Getter
    private SaleOrderID saleOrderID;
    @Getter
    private Date saleOrderDate;
    @Getter
    private CustomerID customerID;
    @Getter
    private List<Product> productList;
    @Getter @Setter
    private DeliveryStatus deliveryStatus;
    @Getter @Setter
    private Date deliveryDate;

    public DeliveryPackage(DeliveryPackageID deliveryPackageID, SaleOrderID saleOrderID, Date saleOrderDate, CustomerID
            customerID, List<Product> productList, DeliveryStatus deliveryStatus){
        this.deliveryPackageID = Identificator.newInstance(DeliveryPackageID.class);
        this.saleOrderID = saleOrderID;
        this.customerID = customerID;
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public Identificator id() {
        return null;
    }
}
