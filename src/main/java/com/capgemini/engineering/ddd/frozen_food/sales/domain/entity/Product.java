package com.capgemini.engineering.ddd.frozen_food.sales.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductID;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.valueObject.Dimensions;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Document(collection = "product_sales")
public class Product implements AggregateRoot, Serializable {

    @Id
    private String id;

    @NotNull
    private ProductID productID;

    @Positive
    private double unitPrice;

//    //NAO VAI SER NECESSARIO PARA CONTEXTO DE VENDAS
//    @NotNull
//    private Unit unit;

    @NotNull
    private Dimensions dimensions;

    @NotBlank
    private String name;

    //shelf life of the product, defined in numbers of days
    @Positive
    private int shelfLife;

    //might be better to use an Enum here
    //something like Availability -> Available, out of stock, discontinued
    private boolean available;

    public Product() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductID getProductID() {
        return productID;
    }

    public void setProductID(ProductID productID) {
        this.productID = productID;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public Identificator id() {
        return this.productID;
    }

    @Override
    public boolean isEqualsTo(Object other) {
        return AggregateRoot.super.isEqualsTo(other);
    }

    @Override
    public int hashcode() {
        return AggregateRoot.super.hashcode();
    }

}
