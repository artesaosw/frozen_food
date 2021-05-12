package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.service;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.Identificator;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.OrderID;
import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.*;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.event.*;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository.ProductionOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class ProductionOrderService {

    @Autowired
    private ProductionOrderRepository productionOrderRepository;

    public ResponseEntity<ProductionOrder> createProductionOrder(@NotNull Map<Product, Integer> itemsOrdered) {

        ProductionOrder productionOrder = new ProductionOrder();

        //this is required since the ProductionOrder class only contains an empty default constructor
        //because JPA requires it
        productionOrder.setProductionOrderID(Identificator.newInstance(ProductionOrderID.class));
        productionOrder.setItemsOrdered(itemsOrdered);

        //persist the new production order
        productionOrder = this.productionOrderRepository.save(productionOrder);

        //prodctionOrder now has an id, let's publish the event
        ProductionOrderIssuedEventPublisher eventPublisher = new ProductionOrderIssuedEventPublisher();
        eventPublisher.publishEvent(productionOrder);

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

}
