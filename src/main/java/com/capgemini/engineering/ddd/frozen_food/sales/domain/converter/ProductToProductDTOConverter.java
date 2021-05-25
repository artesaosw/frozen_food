package com.capgemini.engineering.ddd.frozen_food.sales.domain.converter;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery.ProductDTO;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Product;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.valueObject.Dimensions;

public class ProductToProductDTOConverter {

    public static ProductDTO ConvertProductToProductDTO(Product product) throws CloneNotSupportedException {
        ProductDTO productDTO = new ProductDTO();

        //clone the ProductID (or maybe not)
        //productDTO.setProductID(Identificator.clone(product.getProductID()));
        productDTO.setProductID(product.getProductID());

        productDTO.setDimensions( (Dimensions) product.getDimensions().clone());
        productDTO.setName(product.getName());

        return productDTO;
    }
}
