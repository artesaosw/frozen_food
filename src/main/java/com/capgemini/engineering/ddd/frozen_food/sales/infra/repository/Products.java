package com.capgemini.engineering.ddd.frozen_food.sales.infra.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductID;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Product;

public interface Products extends Repository<Product, ProductID> {
}
