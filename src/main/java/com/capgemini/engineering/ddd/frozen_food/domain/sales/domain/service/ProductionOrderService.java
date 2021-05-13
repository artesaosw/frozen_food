package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.service;

import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.*;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.event.*;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository.ProductionOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductionOrderService {

    @Autowired
    private ProductionOrderRepository productionOrderRepository;

    public ResponseEntity<?> createProductionOrder(@NotNull ProductionOrder productionOrder) {

        if (productionOrder.getItemsOrdered().isEmpty()) {
            return new ResponseEntity<String>("Production orders must contain one or more ordered items.", HttpStatus.BAD_REQUEST);
        }

//        ProductionOrder productionOrder = new ProductionOrder();
//
//        //this is needed since the ProductionOrder class only contains an empty default constructor
//        //since JPA requires it
//        productionOrder.setProductionOrderID(Identificator.newInstance(ProductionOrderID.class));
//        productionOrder.setItemsOrdered(itemsOrdered);

        //persist the new production order and issue the event
        ProductionOrderIssuedEventPublisher eventPublisher = new ProductionOrderIssuedEventPublisher();
        eventPublisher.publishEvent(this.productionOrderRepository.save(productionOrder));

        return new ResponseEntity<ProductionOrder>(productionOrder, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteProductionOrder(@NotBlank String id) {

        try {
            ProductionOrder productionOrder = this.productionOrderRepository.findById(id).get();
            this.productionOrderRepository.deleteById(id);

            //publish the cancellation event
            ProductionOrderCancelledEventPublisher eventPublisher =
                    new ProductionOrderCancelledEventPublisher();
            eventPublisher.publishEvent(productionOrder);

            return new ResponseEntity<String>( "Production Order with ID " +
                    id + " successfully deleted.", HttpStatus.OK);
        }
        //should be more precise with the exceptions to catch
        catch (Exception e) {
            return new ResponseEntity<String>( "Couldn't delete production order with ID"
                    + id + " . There might not exist an order with this ID. ", HttpStatus.BAD_REQUEST);
        }
    }

    public ProductionOrderRepository getProductionOrderRepository() {
        return productionOrderRepository;
    }
}
