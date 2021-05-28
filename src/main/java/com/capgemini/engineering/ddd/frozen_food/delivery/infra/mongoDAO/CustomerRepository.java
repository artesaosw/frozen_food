package com.capgemini.engineering.ddd.frozen_food.delivery.infra.mongoDAO;

import com.capgemini.engineering.ddd.frozen_food.delivery.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.NIF;
import com.capgemini.engineering.ddd.frozen_food.delivery.domain.valueObject.ids.CustomerID;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface CustomerRepository extends MongoRepository<Customer, CustomerID> {

    boolean existsByNif(NIF nif);

    Customer findByNif(NIF nif);
}
