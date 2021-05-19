package com.capgemini.engineering.ddd.frozen_food.menu.domain.repository;

import com.capgemini.engineering.ddd.frozen_food.__metadata.Repository;
import com.capgemini.engineering.ddd.frozen_food._shared.id.OrderID;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Order;

import javax.validation.constraints.NotBlank;

public interface Orders extends Repository<Order, OrderID> {

    boolean existsWithOrderReference(@NotBlank String orderReference);

}
