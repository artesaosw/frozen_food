package com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.service;

import com.capgemini.engineering.ddd.frozen_food.domain.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.Domain;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Customer;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Order;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.OrderState;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.entity.Product;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.event.OrderCancelledEventPublisher;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.event.OrderRegisteredEventPublisher;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.event.PaymentReturnedEventPublisher;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository.OrderRepository;
import com.capgemini.engineering.ddd.frozen_food.domain.sales.domain.repository.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Service
public class OrderServiceImpl implements DomainServices {

    @Autowired
    private OrderRepository orderRepository;

    private Orders orders() {
        return Domain.orders();
    }

    public Order createNewOrder(@NotNull Order order) {

        //TODO
        // 1- VER SE OS PRODUTOS NO MAPA itemsOrdered EXISTEM
        // 2 - VER SE EXISTE QUANTIDADE SUFICIENTE DE CADA PRODUTO PARA VENDA
        // CASO UM DOS PASSOS FALHE, DEVE ATIRAR EXCEÇAO
        // PROVAVELMENTE VAI TER QUE COMUNICAR COM O CONTEXT DE STOCK --- fica mais complicado

        //validate if order can be fulfilled

        //persist new order on the database
        order = this.orderRepository.save(order);

        //issue a OrderRegisteredEvent
        OrderRegisteredEventPublisher eventPublisher = new OrderRegisteredEventPublisher();
        eventPublisher.publishEvent(order);

        return order;
    }

    /*
    Cancels order with the ID passed in and issues an OrderCancelledEvent.
     */
    public void cancelOrder(@NotBlank String id) {

//        Order order = null;
//
//        try {
//
//            //must first fetch the order to change it's OrderState
//            order = this.orderRepository.findById(id).get();
//            order.setOrderState(OrderState.CANCELLED);
//
//            this.orderRepository.delete(order);
//
//            //time to return the money
//            //this implies the money is returned the moment the order is cancelled (deleted)
//            //which might not be the case
//            OrderCancelledEventPublisher cancelledPublisher =
//                    new OrderCancelledEventPublisher();
//            cancelledPublisher.publishEvent(order);
//
//            PaymentReturnedEventPublisher returnedPublisher =
//                    new PaymentReturnedEventPublisher();
//            returnedPublisher.publishEvent(order);
//
//            return new ResponseEntity<String>( "Order with ID " +
//                    id + " successfully deleted.", HttpStatus.OK);
//        }
//        //should be more precise with the exceptions to catch
//        catch (Exception e) {
//            return new ResponseEntity<String>( "Couldn't delete order with id"
//                    + id + " . There might not exist an order with this ID. ", HttpStatus.BAD_REQUEST);
//        }

        //must first fetch the order to change it's OrderState
        Order order = this.orderRepository.findById(id).get();

        if(order.getOrderState() != OrderState.CANCELLED) {
            order.setOrderState(OrderState.CANCELLED);

            this.orderRepository.delete(order);

            //time to return the money
            //this implies the money is returned the moment the order is cancelled (deleted)
            //which might not be the case
            OrderCancelledEventPublisher cancelledPublisher =
                    new OrderCancelledEventPublisher();
            cancelledPublisher.publishEvent(order);

            PaymentReturnedEventPublisher returnedPublisher =
                    new PaymentReturnedEventPublisher();
            returnedPublisher.publishEvent(order);
        }
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }
}
