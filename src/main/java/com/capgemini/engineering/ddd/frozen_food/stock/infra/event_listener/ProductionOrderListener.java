package com.capgemini.engineering.ddd.frozen_food.stock.infra.event_listener;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock.ProductionOrderDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock.ProductionOrderStatusDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.event.production_stock.ProductionOrderRegisteredEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.event.production_stock.ProductionOrderStatusUpdatedEvent;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ProductionOrder;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.ProductionOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import static com.capgemini.engineering.ddd.frozen_food.stock.domain.converter.ProductionOrderConverter.productionOrderDTO2ProductionOrder;

@Service
public class ProductionOrderListener {

    @Autowired
    ProductionOrderDAO productionOrderDAO;

    @EventListener
    public void registerNewProductionOrder(ProductionOrderRegisteredEvent productionOrderRegisteredEvent) {
        ProductionOrderDTO productionOrderDTO = productionOrderRegisteredEvent.getProductionOrderDTO();
        ProductionOrder productionOrder = productionOrderDTO2ProductionOrder(productionOrderDTO);
        if (productionOrderDAO.existsById(productionOrder.id())) {
            // TODO Event to report problem to production domain
            throw new DuplicatedEntityException("Already exists another production order with the same id.");
        }
        if (productionOrderDAO.existsByOrderReference(productionOrder.getOrderReference())) {
            // TODO Event to report problem to production domain
            throw new DuplicatedEntityException("Already exists another production order with the same order reference.");
        }
        productionOrderDAO.save(productionOrder);
    }

    @EventListener
    public void updateProductionOrderStatus(ProductionOrderStatusUpdatedEvent productionOrderStatusUpdatedEvent) {
        ProductionOrderStatusDTO productionOrderStatusDTO = productionOrderStatusUpdatedEvent.getProductionOrderStatusDTO();
        ProductionOrder productionOrder = productionOrderDAO.findById(productionOrderStatusDTO.getId()).get();
        productionOrderStatusDTO.setOrderStatus(productionOrderStatusDTO.getOrderStatus());
        productionOrderDAO.save(productionOrder);
    }
}
