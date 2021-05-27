package com.capgemini.engineering.ddd.frozen_food.sales.domain.service;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductID;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Product;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.repository.ProductRepository;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();

    public Product findById(String id);

    public Product findProductByName(String name);

    public Product findProductByProductId(ProductID productID);

    public Product createNewProduct(Product product);

    public Product updateProduct(Product product);

    public void deleteProduct(String id);

    public void deleteProductByProductId(ProductID productID);

    public ProductRepository getProductRepository();

}
