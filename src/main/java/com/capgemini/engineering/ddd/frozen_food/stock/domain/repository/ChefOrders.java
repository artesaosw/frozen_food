package com.capgemini.engineering.ddd.frozen_food.stock.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food._shared.ChefOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ChefOrder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ChefOrders extends Repository<ChefOrder, ChefOrderID> {

    boolean existsWithOrderReference(@NotBlank String orderReference);

    List<ChefOrder> getAllChefOrdersByOrderStatus(@NotNull OrderStatus orderStatus);
}
