package com.capgemini.engineering.ddd.frozen_food.sales.domain.service;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.OrderDeliveryState;
import com.capgemini.engineering.ddd.frozen_food._shared.ProductionOrderState;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_sales.ProductionOrderDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.events.sales.ProductCreatedEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.events.sales.ProductionOrderCancelledEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.events.sales.ProductionOrderIssuedEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.converter.ProductionOrderToProductionOrderDTO;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.ProductionOrder;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.repository.ProductionOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Service
public class ProductionOrderServiceImpl implements DomainServices, ProductionOrderService {

    @Autowired
    private ProductionOrderRepository productionOrderRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public List<ProductionOrder> findAll() {
        return this.productionOrderRepository.findAll();
    }

    @Override
    public ProductionOrder findById(String id) {
        return this.productionOrderRepository.findById(id).get();
    }

    public ProductionOrder createNewProductionOrder(ProductionOrder productionOrder) {

        //fill in the empty fields
        productionOrder.setProductionOrderID(Identificator.newInstance(ProductionOrderID.class));
        productionOrder.setProductionOrderState(ProductionOrderState.PROCESSING);

        //persist the new production order and issue the event
        productionOrder = this.productionOrderRepository.save(productionOrder);

        ProductionOrderDTO productionOrderDTO =
                ProductionOrderToProductionOrderDTO.convertPOrderToPOrderDTO(productionOrder);
        eventPublisher.publishEvent(new ProductionOrderIssuedEvent(this, productionOrderDTO));

        return productionOrder;
    }

    /*
    Deletes the productionOrder with the specified ID and issues a ProductionOrderCancelledEvent.
     */
    public void deleteProductionOrder(String id) {

        ProductionOrder productionOrder = this.productionOrderRepository.findById(id).get();

        //delete the order with the id given
        this.productionOrderRepository.deleteById(id);

        eventPublisher.publishEvent(new ProductionOrderCancelledEvent(this,
                productionOrder.getProductionOrderID()));
    }

    @Override
    public ProductionOrder updateProductionOrder(ProductionOrder productionOrder) {

        ProductionOrder orderFromDB = this.productionOrderRepository.findById(productionOrder.getId()).get();

        //make sure the new order does not change the ID and ProductionOrderID fields
        //of the order already existing in the db
        if (orderFromDB.getId().equals(productionOrder.getId())
                && orderFromDB.getProductionOrderID().isEqualsTo(productionOrder.getProductionOrderID())) {
            return this.productionOrderRepository.save(orderFromDB);

            //TODO: Should this method launch a ProductionOrderUpdatedEvent ?
            //Only sales cares about those, maybe it doesn't matter
        }

        throw new IllegalArgumentException("Production order update is trying to change immutable fields.");
    }

    public ProductionOrderRepository getProductionOrderRepository() {
        return productionOrderRepository;
    }

    //check https://spring.io/blog/2015/02/11/better-application-events-in-spring-framework-4-2
    //for more information about the @EventListener annotation

    //TODO:  REPLACE THE EVENT IN THE SIGNATURE WITH EVENT THAT PRODUCTION SENDS
    //method to listen to ProductionOrders sent back from the Production context
    //Might trigger methods in this context
    @EventListener
    public void handlePOrderSentFromProduction(ApplicationEvent event) {

        //I assume the event sent by production wraps a ProductionOrderDTO object
        //With the ProductionOrderID from that object, we get the POrder from the sales db
        //and then update the productionOrderState and productionDate fields,
        //since those are the only fields Production can change
        //then we persist the updated entity in the db
        ProductionOrderDTO productionOrderDTO = event.getProductionOrderDTO;

        ProductionOrder pOrder = this.productionOrderRepository.
                findByProductionOrderID(productionOrderDTO.getProductionOrderID()).get();
        pOrder.setProductionOrderState(productionOrderDTO.getProductionOrderState());
        pOrder.setProductionDate(productionOrderDTO.getProductionDate());

        //persist the entity after updating it
        this.productionOrderRepository.save(pOrder);
    }

}
