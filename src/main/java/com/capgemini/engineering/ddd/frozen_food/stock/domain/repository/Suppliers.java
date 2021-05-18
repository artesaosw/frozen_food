package com.capgemini.engineering.ddd.frozen_food.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food._shared.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.NIF;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Supplier;

import javax.validation.constraints.NotNull;

public interface Suppliers extends Repository<Supplier, SupplierID> {

    boolean existsWithNIF(@NotNull NIF nif);

    boolean delete(@NotNull SupplierID id);
}
