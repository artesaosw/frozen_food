package com.capgemini.engineering.ddd.frozen_food.sales.domain.service;

import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.ProductionOrder;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.repository.ProductionOrderRepository;

import javax.validation.constraints.NotBlank;
import java.util.List;

public interface ProductionOrderService {

    public ProductionOrder createNewProductionOrder(ProductionOrder productionOrder);

    public void deleteProductionOrder(String id);

    public List<ProductionOrder> findAll();

    public ProductionOrder findById(String id);

    public ProductionOrderRepository getProductionOrderRepository();

    public ProductionOrder updateProductionOrder(ProductionOrder productionOrder);
}
