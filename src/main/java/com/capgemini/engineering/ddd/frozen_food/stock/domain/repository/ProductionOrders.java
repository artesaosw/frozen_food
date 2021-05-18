package com.capgemini.engineering.ddd.frozen_food.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food._shared.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ProductionOrder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ProductionOrders extends Repository<ProductionOrder, ProductionOrderID> {

    boolean existsWithOrderReference(@NotBlank String orderReference);

    List<ProductionOrder> getAllProductionOrdersByOrderStatus(@NotNull OrderStatus orderStatus);
}
