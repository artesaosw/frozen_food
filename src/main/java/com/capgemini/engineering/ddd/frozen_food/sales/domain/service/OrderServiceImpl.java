package com.capgemini.engineering.ddd.frozen_food.sales.domain.service;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery.DeliveryOrderDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.event.sales_delivery.OrderCancelledEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.event.sales_delivery.OrderRegisteredEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.event.sales_delivery.PaymentReturnedEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.OrderID;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.converter.OrderToOrderDTOConverter;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Order;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.exception.CustomerDoesNotExistException;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.exception.OrderAlreadyCancelled;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.exception.OrderAlreadyExistsException;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.exception.OrderDoesNotExist;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.repository.CustomerRepository;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements DomainServices, OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order getOrderById(String id) {
        return this.orderRepository.findById(id).get();
    }

    public Order registerNewOrder(Order order) throws CloneNotSupportedException {

        //make sure the order passed as argument contains a null id or is blank (doesn't exist in the db)
        //IF so, attempt to persist it, otherwise, throw OrderAlreadyExistException
        if (order.getId() == null || order.getId().isBlank()) {

            //If order doesn't exist, make sure the customer is already registered
            //Can't place orders before registering the customer that places it
            Optional<Customer> customer = this.customerRepository.findById(order.getOrderedBy().getId());

            if( customer.isEmpty() ) {
                throw new CustomerDoesNotExistException("Customer associated with order doesn't exist. Please register before proceeding.");
            }

            //fill up the remaining attributes of the order object
            order.setOrderedBy(customer.get());
            order.setOrderID(Identificator.newInstance(OrderID.class));
            order.setOrderStatus(OrderStatus.PROCESSING);
            order.setCreationDate(LocalDate.now());
            this.orderRepository.save(order);

            //issue OrderRegisteredEvent
            DeliveryOrderDTO deliveryOrderDTO = OrderToOrderDTOConverter.convertOrderToOrderDTO(order);
            this.eventPublisher.publishEvent(new OrderRegisteredEvent(this, deliveryOrderDTO));
        }
        else {
            throw new OrderAlreadyExistsException("The order specified may already exist, or it may contain and invalid ID. If you wish to modify an existing order, delete it" +
                    "and replace it with a new one.");
        }

        return order;
    }

    /*
    Cancels order with the ID passed in and issues an OrderCancelledEvent.
     */
    public void cancelOrder(@NotBlank String id) throws CloneNotSupportedException {

        //must first fetch the order to change it's OrderDeliveryState
        Order order = this.orderRepository.findById(id).get();

        if(order.getOrderStatus() != OrderStatus.CANCELED) {
            order.setOrderStatus(OrderStatus.CANCELED);
            this.orderRepository.save(order);

            //TODO: can assume we must keep every order in the db and can't delete them for good ?
//            this.orderRepository.delete(order);

            //time to return the money
            //this implies the money is returned the moment the order is cancelled
            //which might not be the case
            this.eventPublisher.publishEvent(new PaymentReturnedEvent(this, order));

            //publish an OrderCancelledEvent
            DeliveryOrderDTO deliveryOrderDTO = OrderToOrderDTOConverter.convertOrderToOrderDTO(order);
            this.eventPublisher.publishEvent(new OrderCancelledEvent(this, deliveryOrderDTO));
        }
        else {
            throw new OrderAlreadyCancelled("Order with id " + id + " is already cancelled.");
        }
    }

    //CONSIDER: in practice, you delete an already existing order and place a new order
    public void updateOrder(Order order) {

        //make sure an order with id = order.getId() already exists in the db
        //IF so, update it, otherwise, throw OrderDoesNotExistException
        if (this.orderRepository.existsById(order.getId())) {
            this.orderRepository.save(order);

            //issue OrderUpdatedEvent
        }
        else {
            throw new OrderDoesNotExist("The order specified hasn't been registered yet. Register it before modifying.");
        }
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    //TODO: REPLACE THE EVENT IN THE SIGNATURE WITH EVENT
    //THE DELIVERY CONTEXT ISSUES WHENEVER AN ORDER IS UPDATED
    @EventListener
    public void handleDeliveryOrderUpdatedEvent(ApplicationEvent event) {
        // convert entity sent by the Delivery context into a Product (if it's not converted already)
        // then update it into the database by calling the method

        //TODO: UNCOMMENT WHEN THE CORRECT EVENT IS ADDED

//        //I'm assuming the event sent wraps an DeliverOrderDTO
//        DeliveryOrderDTO deliveryOrderDTO = event.DeliveryOrderDTO();
//
//        //find the order in the db based on the OrderID of deliveryOrderDTO
//        Order order = this.orderRepository.findByOrderID(deliveryOrderDTO.getOrderID()).get();
//
//        //update the order object with values from deliveryOrderDTO
//        //Obviously, the ID and OrderID are immutable and can't be changed
//        order.setOrderStatus(deliveryOrderDTO.getOrderStatus());
//        order.setDeliveryDate(deliveryOrderDTO.getDeliveryDate());
//
//        //persis the updated order object
//        this.orderRepository.save(order);
    }

}
