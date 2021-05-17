package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.converter;

import com.capgemini.engineering.ddd.frozen_food.domain.delivery.external.ProductDTO;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.saleOrder.Product;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids.ProductID;

public class ProductDTOConverter {

    private UnitsConverter unitsConverter;

    public Product productDTOtoProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setProductID(new ProductID(productDTO.getProductID()));
        product.setQuantity(productDTO.getProductQuantity());
        product.setWeightUnit(unitsConverter.whichUnit(productDTO.getWeightUnit()));
        product.setWeight(productDTO.getWeight());
        product.setVolumeUnit(unitsConverter.whichUnit(productDTO.getVolumeUnit()));
        product.setVolume(productDTO.getVolume());
        product.setLengthUnit(unitsConverter.whichUnit(productDTO.getLengthUnit()));
        product.setHeight(productDTO.getHeight());
        product.setWidth(productDTO.getWidth());
        return product;
    }
}
