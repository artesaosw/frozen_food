package com.capgemini.engineering.ddd.frozen_food.delivery.domain.converter;

import com.capgemini.engineering.ddd.frozen_food.delivery.external.ProductDTO;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.ProductReplica;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.ProductID;

public class ProductDTOConverter {

    private UnitsConverter unitsConverter;

    public ProductReplica productDTOtoProduct(ProductDTO productDTO){
        ProductReplica productReplica = new ProductReplica();
        productReplica.setProductID(new ProductID(productDTO.getProductID()));
        productReplica.setWeightUnit(unitsConverter.whichUnit(productDTO.getWeightUnit()));
        productReplica.setWeight(productDTO.getWeight());
        productReplica.setVolumeUnit(unitsConverter.whichUnit(productDTO.getVolumeUnit()));
        productReplica.setVolume(productDTO.getVolume());
        productReplica.setLengthUnit(unitsConverter.whichUnit(productDTO.getLengthUnit()));
        productReplica.setHeight(productDTO.getHeight());
        productReplica.setWidth(productDTO.getWidth());
        return productReplica;
    }
}
