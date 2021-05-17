package com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.ids.ProductID;
import com.capgemini.engineering.ddd.frozen_food.domain.delivery.model.domain.entity.saleOrder.Product;

public interface ProductRepository extends Repository<Product, ProductID> {

}
