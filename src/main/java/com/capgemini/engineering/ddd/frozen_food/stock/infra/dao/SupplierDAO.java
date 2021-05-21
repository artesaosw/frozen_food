package com.capgemini.engineering.ddd.frozen_food.stock.infra.dao;

import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.NIF;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SupplierDAO extends MongoRepository<Supplier, SupplierID> {

    boolean existsByNif(NIF nif);

    Supplier findByNif(NIF nif);

    boolean existsByName(String name);

    Supplier findByName(String name);
}
