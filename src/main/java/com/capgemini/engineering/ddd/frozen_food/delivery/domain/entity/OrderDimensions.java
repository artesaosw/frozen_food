package com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.ids.SaleOrderID;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository.SaleOrderReplicaRepository;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

public class OrderDimensions implements Serializable {

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

    private SaleOrderReplicaRepository saleOrderReplicaRepository;

    public OrderDimensions (SaleOrderID saleOrderID){

         this.orderWeight = 0;
         this.orderVolume = 0;
         this.orderHeight = 0;
         this.orderWidth = 0;
         this.weightUnit = Units.KG;
         this.volumeUnit = Units.L;
         this.lengthUnit = Units.CM;
         for(Map.Entry<ProductReplica, Integer> p : saleOrderReplicaRepository.withId(saleOrderID).getSaleOrderProductsList().entrySet()) {
             if(p.getKey().getWeightUnit() == weightUnit){
                 this.orderWeight = orderWeight + p.getKey().getWeight() * p.getValue();
             }else{
                 this.orderWeight = (float) (orderWeight + (p.getKey().getWeight() * p.getValue()) * convertWeightUnit(p.getKey().getWeightUnit()));
             }
             if(p.getKey().getVolumeUnit() == volumeUnit){
                 this.orderVolume = orderVolume + p.getKey().getVolume() * p.getValue();
             }else{
                 this.orderVolume = (float) (orderVolume + (p.getKey().getVolume() * p.getValue())* convertVolumeUnit(p.getKey().getVolumeUnit()));
             }
             if(p.getKey().getLengthUnit() == lengthUnit){
                 this.orderHeight = orderHeight + p.getKey().getHeight() * p.getValue();
             }else{
                 this.orderHeight = (float) (orderHeight + (p.getKey().getHeight() * p.getValue()) * convertLengthUnit(p.getKey().getLengthUnit()));
             }
             if(p.getKey().getLengthUnit() == lengthUnit){
                 this.orderWidth = orderWidth + p.getKey().getWidth() * p.getValue();
             }else{
                 this.orderWidth = (float) (orderWidth + (p.getKey().getWidth()) * convertLengthUnit(p.getKey().getLengthUnit()));
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
