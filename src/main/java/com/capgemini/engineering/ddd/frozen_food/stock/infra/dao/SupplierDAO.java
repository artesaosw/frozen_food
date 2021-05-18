package com.capgemini.engineering.ddd.frozen_food.stock.infra.dao;

import com.capgemini.engineering.ddd.frozen_food._shared.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.NIF;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SupplierDAO extends MongoRepository<Supplier, SupplierID> {

    boolean existsByNif(NIF nif);

    Supplier findByName(String name);
}
