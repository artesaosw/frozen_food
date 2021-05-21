package com.capgemini.engineering.ddd.frozen_food.menu.domain.service;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.OrderID;
import com.capgemini.engineering.ddd.frozen_food.menu.Menu;
import com.capgemini.engineering.ddd.frozen_food._shared.menu.dto.OrderDTO;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.converter.OrderConverter;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Order;
import com.capgemini.engineering.ddd.frozen_food._shared.menu.events.StockOrderRegisteredPublisher;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.repository.Orders;
import com.capgemini.engineering.ddd.frozen_food.menu.infra.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class MantainOrders implements DomainServices {

    @Autowired
    OrderDAO orderDAO;

    private Orders orders() { return Menu.orders();}


    public Order getOrderById(OrderID id) throws NonExistentEntityException {
        if(!orderDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
       return orderDAO.findById(id).get();
    }

    public List<Order> getAllOrders(){
        return orderDAO.findAll();
    }


    public void registerNew(@NotEmpty String orderReference, @NotEmpty Map<Ingredient, Integer> articles) throws DuplicatedEntityException {

        //Validation
        //if (orders().existsWithOrderReference(orderReference)){
        //    throw new IllegalArgumentException("There is already an order with this reference");
        //}

        //Validation
        if(orderDAO.existsByOrderReference(orderReference)){
            throw new DuplicatedEntityException("There is already an order with this reference");
        }

        //Instantiate aggregate
        Order order = new Order(orderReference, articles);

        //persists
        //orders().registerNew(order);
        orderDAO.save(order);

        //converts order to orderDTO
        OrderDTO orderDTO = OrderConverter.order2orderDTO(order);

        //reports event
        StockOrderRegisteredPublisher eventPublisher = new
                StockOrderRegisteredPublisher();

        eventPublisher.publishEvent(orderDTO);
    }

    public void updateOrder(Order order) throws NonExistentEntityException {
        if (!orderDAO.existsById(order.getId())) {
            throw new NonExistentEntityException("There is no order with id = " + order.getId());
        }
        orderDAO.save(order);
    }                    //---------------> criar evento de update order?

    public void deleteOrder(OrderID id) throws NonExistentEntityException {
        if(!orderDAO.existsById(id)){
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
        orderDAO.delete(orderDAO.findById(id).get());
    }
}
