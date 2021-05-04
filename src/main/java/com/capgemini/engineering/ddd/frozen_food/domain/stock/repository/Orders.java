package com.capgemini.engineering.ddd.frozen_food.domain.stock.repository;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.entity.Order;

import javax.validation.constraints.NotBlank;

public interface Orders extends Repository<Order, OrderID> {

    boolean existsWithOrderReference(@NotBlank String orderReference);
}
