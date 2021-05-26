package com.capgemini.engineering.ddd.frozen_food.delivery.infra.mongoDAO;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.Product;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.ProductID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductDatabase extends MongoRepository<Product, ProductID> {

}
