package com.capgemini.engineering.ddd.frozen_food.delivery.domain.converter;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.Product;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.ProductID;
import com.capgemini.engineering.ddd.frozen_food.delivery.external.ProductDTO;

public class ProductDTOConverter {

    private UnitsConverter unitsConverter;

    public Product productDTOtoProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setProductID(new ProductID(productDTO.getProductID()));
        /*product.setWeightUnit(unitsConverter.whichUnit(productDTO.getWeightUnit()));
        product.setWeight(productDTO.getWeight());
        product.setVolumeUnit(unitsConverter.whichUnit(productDTO.getVolumeUnit()));
        product.setVolume(productDTO.getVolume());
        product.setLengthUnit(unitsConverter.whichUnit(productDTO.getLengthUnit()));
        product.setHeight(productDTO.getHeight());
        product.setWidth(productDTO.getWidth());*/
        return product;
    }
}
