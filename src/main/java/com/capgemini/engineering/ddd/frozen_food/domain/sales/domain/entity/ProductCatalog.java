package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.AggregateRoot;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductCatalogID;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//ESTA CLASSE PODE NEM SER NECESSARIA. PARA OBTER UM CATALOG DOS PRODUTOS, FAZ-SE UMA QUERY À BASE DE DADOS
public class ProductCatalog implements AggregateRoot, Serializable {

    private String id;

    private ProductCatalogID productCatalogID;

    private Set<Product> products = new HashSet<>();

    public ProductCatalog() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductCatalogID getProductCatalogID() {
        return productCatalogID;
    }

    public void setProductCatalogID(ProductCatalogID productCatalogID) {
        this.productCatalogID = productCatalogID;
    }

    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public Identificator id() {
        return this.productCatalogID;
    }

    @Override
    public boolean isEqualsTo(Object other) {
        return AggregateRoot.super.isEqualsTo(other);
    }

    @Override
    public int hashcode() {
        return AggregateRoot.super.hashcode();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void addRemove(Product product) {
        this.products.remove(product);
    }
}
