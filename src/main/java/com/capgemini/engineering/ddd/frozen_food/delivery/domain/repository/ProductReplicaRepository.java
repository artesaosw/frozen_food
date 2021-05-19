package com.capgemini.engineering.ddd.frozen_food.delivery.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.ProductID;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.ProductReplica;

public interface ProductReplicaRepository extends Repository<ProductReplica, ProductID> {

}
