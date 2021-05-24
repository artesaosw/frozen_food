package com.capgemini.engineering.ddd.frozen_food.sales.domain.service;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.OrderDeliveryState;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.sales_delivery.OrderDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.events.sales.OrderCancelledEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.events.sales.OrderRegisteredEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.events.sales.PaymentReturnedEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.OrderID;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.converter.OrderToOrderDTOConverter;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.entity.Order;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.exception.OrderAlreadyCancelled;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.exception.OrderAlreadyExistsException;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.exception.OrderDoesNotExist;
import com.capgemini.engineering.ddd.frozen_food.sales.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements DomainServices, OrderService {

    @Autowired
    private OrderRepository orderRepository;

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

        //make sure an order with id = order.getId() doesn't already exist in the db
        //IF so, persist it, otherwise, throw OrderAlreadyExistException
        if (!this.orderRepository.existsById(order.getId())) {

            //fill up the remaining attributes of the order object
            order.setOrderID(Identificator.newInstance(OrderID.class));
            order.setOrderDeliveryState(OrderDeliveryState.PROCESSING);
            order.setCreationDate(LocalDate.now());
            this.orderRepository.save(order);

            //issue OrderRegisteredEvent
            OrderDTO orderDTO = OrderToOrderDTOConverter.convertOrderToOrderDTO(order);
            this.eventPublisher.publishEvent(new OrderRegisteredEvent(this, orderDTO));
        }
        else {
            throw new OrderAlreadyExistsException("The order specified already exists. If you wish to modify an existing order, delete it" +
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

        if(order.getOrderDeliveryState() != OrderDeliveryState.CANCELLED) {
            order.setOrderDeliveryState(OrderDeliveryState.CANCELLED);

            //let's assume we must keep every order in the db and can't delete them for good
//            this.orderRepository.delete(order);

            //time to return the money
            //this implies the money is returned the moment the order is cancelled
            //which might not be the case
            this.eventPublisher.publishEvent(new PaymentReturnedEvent(this, order));

            //publish an OrderCancelledEvent
            OrderDTO orderDTO = OrderToOrderDTOConverter.convertOrderToOrderDTO(order);
            this.eventPublisher.publishEvent(new OrderCancelledEvent(this, orderDTO));
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
}
