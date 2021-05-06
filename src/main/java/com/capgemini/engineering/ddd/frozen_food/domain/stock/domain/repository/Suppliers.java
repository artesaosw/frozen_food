package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.NIF;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.Supplier;

import javax.validation.constraints.NotNull;

public interface Suppliers extends Repository<Supplier, SupplierID> {

    boolean existsWithNIF(@NotNull NIF nif);
}
