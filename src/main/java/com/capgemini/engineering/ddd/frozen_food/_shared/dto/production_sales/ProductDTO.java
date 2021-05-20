package com.capgemini.engineering.ddd.frozen_food._shared.dto.production_sales;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductID;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.valueObject.Dimensions;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductDTO {

    @NotNull
    private ProductID productID;

    @NotNull
    private Dimensions dimensions;

    @NotBlank
    private String name;

    private boolean available;

    public ProductDTO() {

    }

    public ProductDTO(ProductID productID, Dimensions dimensions, String name, boolean available) {
        this.productID = productID;
        this.dimensions = dimensions;
        this.name = name;
        this.available = available;
    }

    public ProductID getProductID() {
        return productID;
    }

    public void setProductID(ProductID productID) {
        this.productID = productID;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
