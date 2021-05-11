package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String>, Products {
}
