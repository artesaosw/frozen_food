package com.capgemini.engineering.ddd.frozen_food.domain.delivery.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.Units;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.ids.SaleOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.saleOrder.SaleOrder;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.saleOrder.SaleOrderRepository;
import lombok.Getter;

public class OrderDimensions {

    private Units weightUnit;

    @Getter
    private float orderWeight;

    private Units volumeUnit;
    @Getter
    private float orderVolume;

    private Units heightUnit;
    @Getter
    private float orderHeight;

    private Units widthUnit;
    
    @Getter
    private float orderWidth;

    private SaleOrderID saleOrderID;

    private SaleOrderRepository saleOrderRepository;

    public OrderDimensions (SaleOrderID saleOrderID){
         saleOrderRepository.withId(saleOrderID);
         orderWeight = 0;
         orderVolume = 0;
         orderHeight = 0;
         orderWidth = 0;
         for(Product p : saleOrderRepository.withId(saleOrderID).getSaleOrderProducts()){
             this.orderWeight = orderWeight + p.getWeight();
             this.orderVolume = orderVolume + p.getVolume();
             this.orderHeight = orderHeight + p.getHeight();
             this.orderWidth = orderWidth + p.getWidth();
         }
    }
}
