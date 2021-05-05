package com.capgemini.engineering.ddd.frozen_food.domain.delivery.entity;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.*;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ids.ClientID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ids.DeliveryOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ids.SaleOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.StatusDelivery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
public class DeliveryOrder {

    @Id @Getter
    private DeliveryOrderID deliveryOrderID;
    @Getter
    private SaleOrderID saleOrderID;
    @Getter
    private ClientID clientID;
    @Getter @Setter
    private StatusDelivery statusDelivery;
    @Getter @Setter
    private Date deliveryDate;

    public DeliveryOrder(DeliveryOrderID deliveryOrderID, SaleOrderID saleOrderID, ClientID clientID,
        StatusDelivery statusDelivery){
        this.deliveryOrderID = Identificator.newInstance(DeliveryOrderID.class);
        this.saleOrderID = saleOrderID;
        this.clientID = clientID;
        this.statusDelivery = statusDelivery;
    }


}
