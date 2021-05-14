package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.SupplierOrder;

import javax.validation.constraints.NotBlank;

public interface SuppliersOrders extends Repository<SupplierOrder, SupplierOrderID> {

    boolean existsWithOrderReference(@NotBlank String orderReference);
}
