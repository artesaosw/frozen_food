package com.capgemini.engineering.ddd.frozen_food.stock.infra.dao;

import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.SupplierOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.SupplierOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SupplierOrderDAO extends MongoRepository<SupplierOrder, SupplierOrderID> {

    boolean existsByOrderReference(String orderReference);

    List<SupplierOrder> findAllByOrderStatus(OrderStatus orderStatus);
}
