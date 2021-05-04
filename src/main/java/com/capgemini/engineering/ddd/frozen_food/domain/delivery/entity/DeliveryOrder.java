package com.capgemini.engineering.ddd.frozen_food.domain.delivery.entity;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.*;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.StatusDelivery;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class DeliveryOrder {

    private DeliveryOrderID deliveryOrderID;
    private SaleOrderID saleOrderID;
    private ClientID clientID;
    private StatusDelivery statusDelivery;
    private Date deliveryDate;

    public DeliveryOrder(DeliveryOrderID deliveryOrderID, SaleOrderID saleOrderID, ClientID clientID,
        StatusDelivery statusDelivery){
        this.deliveryOrderID = Identificator.newInstance(DeliveryOrderID.class);
        this.saleOrderID = saleOrderID;
        this.clientID = clientID;
        this.statusDelivery = statusDelivery;
    }

    public DeliveryOrder(DeliveryOrderID deliveryOrderID, SaleOrderID saleOrderID, ClientID clientID,
                         StatusDelivery statusDelivery, @NotBlank Date deliveryDate){
        this.deliveryOrderID = Identificator.newInstance(DeliveryOrderID.class);
        this.saleOrderID = saleOrderID;
        this.clientID = clientID;
        this.statusDelivery = statusDelivery;
        this.deliveryDate = deliveryDate;
    }


}
