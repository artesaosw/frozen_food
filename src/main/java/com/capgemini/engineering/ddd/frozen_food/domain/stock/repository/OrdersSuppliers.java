package com.capgemini.engineering.ddd.frozen_food.domain.stock.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderSupplierID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.entity.OrderSupplier;

import javax.validation.constraints.NotBlank;

public interface OrdersSuppliers extends Repository<OrderSupplier, OrderSupplierID> {

    boolean existsWithOrderReference(@NotBlank String orderReference);
}
