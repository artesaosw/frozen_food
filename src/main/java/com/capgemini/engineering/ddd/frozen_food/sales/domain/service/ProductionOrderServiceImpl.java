package com.capgemini.engineering.ddd.frozen_food.sales.domain.service;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Product;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.ProductionOrder;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.exception.ProductionOrderContainsNoItemException;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.repository.ProductionOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Service
public class ProductionOrderServiceImpl implements DomainServices, ProductionOrderService {

    @Autowired
    private ProductionOrderRepository productionOrderRepository;

    public ProductionOrder createProductionOrder(@NotNull ProductionOrder productionOrder) {

        if (this.validateItemsOrderedMap(productionOrder.getProducts())) {
            throw new ProductionOrderContainsNoItemException("Production order must contain one or more ordered items.");
        }

        //persist the new production order and issue the event
        productionOrder = this.productionOrderRepository.save(productionOrder);

//        ProductionOrderIssuedEventPublisher eventPublisher = new ProductionOrderIssuedEventPublisher();
//        eventPublisher.publishEvent(productionOrder);

        return productionOrder;
    }

    /*
    Deletes the productionOrder with the specified ID and issues a ProductionOrderCancelledEvent.
     */
    public void deleteProductionOrder(@NotBlank String id) {

//        try {
//            ProductionOrder productionOrder = this.productionOrderRepository.findById(id).get();
//            this.productionOrderRepository.deleteById(id);
//
//            //publish the cancellation event
//            ProductionOrderCancelledEventPublisher eventPublisher =
//                    new ProductionOrderCancelledEventPublisher();
//            eventPublisher.publishEvent(productionOrder);
//
//            return new ResponseEntity<String>( "Production Order with ID " +
//                    id + " successfully deleted.", HttpStatus.OK);
//        }
//        //should be more precise with the exceptions to catch
//        catch (Exception e) {
//            return new ResponseEntity<String>( "Couldn't delete production order with ID"
//                    + id + " . There might not exist an order with this ID. ", HttpStatus.BAD_REQUEST);
//        }

        ProductionOrder productionOrder = this.productionOrderRepository.findById(id).get();
        this.productionOrderRepository.deleteById(id);

        //publish the cancellation event
//        ProductionOrderCancelledEventPublisher eventPublisher =
//                new ProductionOrderCancelledEventPublisher();
//        eventPublisher.publishEvent(productionOrder);
    }

    public ProductionOrderRepository getProductionOrderRepository() {
        return productionOrderRepository;
    }

    /*
    Checks if the itemsOrdered map contains valid data (>= 0 quantities ordered) and also if it's not empty.
    Products with <= 0 quantities ordered are removed from the map.
    Returns false if empty. Otherwise, returns true.
     */
    private boolean validateItemsOrderedMap(Map<String, Integer> itemsOrdered) {

        //remove products with non-positive quantities
        for (String product : itemsOrdered.keySet()) {
            if (itemsOrdered.get(product) <= 0) {
                itemsOrdered.remove(product);
            }
        }

        //check if map is empty
        if (itemsOrdered.isEmpty()) {
            return false;
        }

        //if it's not empty, then it's valid
        return true;
    }
}
