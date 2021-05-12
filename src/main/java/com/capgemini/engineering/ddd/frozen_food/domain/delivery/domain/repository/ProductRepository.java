package com.capgemini.engineering.ddd.frozen_food.domain.delivery.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.shared.ids.ProductID;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.domain.entity.Product;

public interface ProductRepository extends Repository<Product, ProductID> {

}
