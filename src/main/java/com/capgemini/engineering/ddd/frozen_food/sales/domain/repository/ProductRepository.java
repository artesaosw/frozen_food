package com.capgemini.engineering.ddd.frozen_food.sales.domain.repository;

import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductID;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String>, Products {

    public boolean existsProductByName(String name);

    public Optional<Product> findByName(String id);

    public Optional<Product> findByProductID(ProductID productID);

    public void deleteByProductID(ProductID productID);

}
