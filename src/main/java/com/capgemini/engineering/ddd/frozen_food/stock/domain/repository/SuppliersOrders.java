package com.capgemini.engineering.ddd.frozen_food.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food._shared.SupplierOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.SupplierOrder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface SuppliersOrders extends Repository<SupplierOrder, SupplierOrderID> {

    boolean existsWithOrderReference(@NotBlank String orderReference);

    List<SupplierOrder> getAllSuppliersOrdersByOrderStatus(@NotNull OrderStatus orderStatus);
}
