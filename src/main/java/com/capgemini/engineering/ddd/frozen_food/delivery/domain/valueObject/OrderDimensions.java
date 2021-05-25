package com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository.SaleOrderReplicaRepository;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.SaleOrderID;
import lombok.Getter;

public class OrderDimensions {

    @Getter
    private Units weightUnit;

    @Getter
    private double orderWeight;

    @Getter
    private Units volumeUnit;
    @Getter
    private double orderVolume;

    @Getter
    private Units lengthUnit;
    @Getter
    private double orderHeight;
    @Getter
    private double orderWidth;

    @Getter
    private SaleOrderID saleOrderID;

    private SaleOrderReplicaRepository saleOrderReplicaRepository;

    public OrderDimensions (SaleOrderID saleOrderID){

         this.saleOrderID = saleOrderID;
         this.weightUnit = Units.KG;
         this.volumeUnit = Units.L;
         this.lengthUnit = Units.CM;

         this.orderWeight = 0;
         this.orderVolume = 0;
         this.orderHeight = 0;
         this.orderWidth = 0;

         /*for(Map.Entry<Product, Integer> p : saleOrderReplicaRepository.withId(saleOrderID).getSaleOrderProductsList().entrySet()) {
             if(p.getKey().getDimensions().getWeightUnit() == weightUnit){
                 this.orderWeight = orderWeight + p.getKey().getDimensions().getWeight() * p.getValue();
             }else{
                 this.orderWeight = (orderWeight + (p.getKey().getDimensions().getWeight() * p.getValue()) * convertWeightUnit(p.getKey().getDimensions().getWeightUnit()));
             }
             if(p.getKey().getDimensions().getVolumeUnit() == volumeUnit){
                 this.orderVolume = orderVolume + p.getKey().getDimensions().getVolume() * p.getValue();
             }else{
                 this.orderVolume = (orderVolume + (p.getKey().getDimensions().getVolume() * p.getValue())* convertVolumeUnit(p.getKey().getDimensions().getVolumeUnit()));
             }
             if(p.getKey().getDimensions().getLengthUnit() == lengthUnit){
                 this.orderHeight = orderHeight + p.getKey().getDimensions().getHeight() * p.getValue();
             }else{
                 this.orderHeight = (orderHeight + (p.getKey().getDimensions().getHeight() * p.getValue()) * convertLengthUnit(p.getKey().getDimensions().getLengthUnit()));
             }
             if(p.getKey().getDimensions().getLengthUnit() == lengthUnit){
                 this.orderWidth = orderWidth + p.getKey().getDimensions().getWidth() * p.getValue();
             }else{
                 this.orderWidth = (orderWidth + (p.getKey().getDimensions().getWidth()) * convertLengthUnit(p.getKey().getDimensions().getLengthUnit()));
             }
         }*/
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

    @Override
    public boolean equals(Object objectOrderDimensions){
        if(this == objectOrderDimensions){
            return true;
        }
        if(objectOrderDimensions == null || getClass() != objectOrderDimensions.getClass()){
            return false;
        }
        OrderDimensions orderDimensions = (OrderDimensions) objectOrderDimensions;
        return this.orderWeight == orderDimensions.getOrderWeight() &&
               this.orderVolume == orderDimensions.getOrderVolume() &&
               this.orderHeight == orderDimensions.getOrderHeight() &&
               this.orderWidth == orderDimensions.getOrderWidth();
    }

}
