package com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.Product;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.ProductID;

public interface ProductRepository extends Repository<Product, ProductID> {

}