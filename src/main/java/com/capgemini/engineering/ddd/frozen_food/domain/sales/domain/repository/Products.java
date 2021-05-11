package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductID;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Product;

public interface Products extends Repository<Product, ProductID> {
}
