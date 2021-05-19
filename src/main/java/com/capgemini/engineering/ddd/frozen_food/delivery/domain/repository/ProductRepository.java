package com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.ids.ProductID;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.ProductReplica;

public interface ProductRepository extends Repository<ProductReplica, ProductID> {

}
