package com.capgemini.engineering.ddd.frozen_food.sales.infra.repository;

import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String>, Products {

    public boolean existsProductByName(String name);
}
