package com.capgemini.engineering.ddd.frozen_food.domain.delivery.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.Units;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.ids.SaleOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.saleOrder.SaleOrderRepository;
import lombok.Getter;

public class OrderDimensions {

    @Getter
    private Units weightUnit;

    @Getter
    private float orderWeight;

    @Getter
    private Units volumeUnit;
    @Getter
    private float orderVolume;

    @Getter
    private Units lengthUnit;
    @Getter
    private float orderHeight;
    @Getter
    private float orderWidth;

    @Getter
    private SaleOrderID saleOrderID;

    private SaleOrderRepository saleOrderRepository;

    public OrderDimensions (SaleOrderID saleOrderID){

         this.orderWeight = 0;
         this.orderVolume = 0;
         this.orderHeight = 0;
         this.orderWidth = 0;
         this.weightUnit = saleOrderRepository.withId(saleOrderID).getSaleOrderProducts().get(0).getWeightUnit();
         this.volumeUnit = saleOrderRepository.withId(saleOrderID).getSaleOrderProducts().get(0).getVolumeUnit();
         this.lengthUnit = saleOrderRepository.withId(saleOrderID).getSaleOrderProducts().get(0).getHeightUnit();

         for(Product p : saleOrderRepository.withId(saleOrderID).getSaleOrderProducts()){
             if(p.getWeightUnit() == weightUnit){
                 this.orderWeight = orderWeight + p.getWeight();
             }else{
                 this.orderWeight = (float) (orderWeight + p.getWeight() * convertWeightUnit(p.getWeightUnit()));
             }
             if(p.getVolumeUnit() == volumeUnit){
                 this.orderVolume = orderVolume + p.getVolume();
             }else{
                 this.orderVolume = (float) (orderVolume + p.getVolume() * convertVolumeUnit(p.getVolumeUnit()));
             }
             if(p.getHeightUnit() == lengthUnit){
                 this.orderHeight = orderHeight + p.getHeight();
             }else{
                 this.orderHeight = (float) (orderHeight + p.getHeight() * convertLengthUnit(p.getHeightUnit()));
             }
             if(p.getWidthUnit() == lengthUnit){
                 this.orderWidth = orderWidth + p.getWidth();
             }else{
                 this.orderWidth = (float) (orderWidth + p.getWidth() * convertLengthUnit(p.getWidthUnit()));
             }
         }
    }

    public double convertWeightUnit(Units weightProductUnit){
        double value = 1;
        if(weightUnit == Units.KG){
            if(weightProductUnit == Units.G){
                value = (1/Math.pow(10,3));
            }
            if(weightProductUnit == Units.MG){
                value = (1/Math.pow(10,6));
            }
        }
        if(weightUnit == Units.G){
            if(weightProductUnit == Units.KG){
                value = (Math.pow(10,3));
            }
            if(weightProductUnit == Units.MG){
                value = (1/Math.pow(10,3));
            }
        }
        if(weightUnit == Units.MG){
            if(weightProductUnit == Units.KG){
                value = (Math.pow(10,6));
            }
            if(weightProductUnit == Units.G){
                value = (Math.pow(10,3));
            }
        }
        return value;
    }

    public double convertVolumeUnit(Units volumeProductUnit){
        double value = 1;
        if(volumeUnit == Units.L){
            if(volumeProductUnit == Units.ML){
                value = (1/Math.pow(10,3));
            }
        }
        if(volumeUnit == Units.ML){
            if(volumeProductUnit == Units.L){
                value = (Math.pow(10,3));
            }
        }
        return value;
    }

    public double convertLengthUnit(Units lengthProductUnit){
        double value = 1;
        if(lengthUnit == Units.M){
            if(lengthProductUnit == Units.CM){
                value = (1/Math.pow(10,2));
            }
        }
        if(lengthUnit == Units.CM){
            if(lengthProductUnit == Units.M){
                value = (Math.pow(10,2));
            }
        }
        return value;
    }
}
