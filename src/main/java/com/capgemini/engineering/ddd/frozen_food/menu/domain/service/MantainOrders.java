package com.capgemini.engineering.ddd.frozen_food.menu.domain.service;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.event.menu_stock.ChefOrderRegisteredEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ChefOrderID;
import com.capgemini.engineering.ddd.frozen_food._shared.menu.events.StockOrderRegisteredEvent;
import com.capgemini.engineering.ddd.frozen_food.menu.Menu;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.converter.OrderConverter;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.entity.Order;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.menu.domain.repository.Orders;
import com.capgemini.engineering.ddd.frozen_food.menu.infra.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Service
public class MantainOrders implements DomainServices {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    OrderDAO orderDAO;

    private Orders orders() { return Menu.orders();}


    public Order getOrderById(ChefOrderID id) throws NonExistentEntityException {
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
        if(orderDAO.existsByOrderReference(orderReference)){
            throw new DuplicatedEntityException("There is already an order with this reference");
        }

        //Instantiate aggregate
        Order order = new Order(orderReference, articles);

        //persists
        //orders().registerNew(order);
        orderDAO.save(order);

        //converts order to orderDTO
        ChefOrderDTO chefOrderDTO = OrderConverter.order2chefOrderDTO(order);

        //reports event
        applicationEventPublisher.publishEvent(new ChefOrderRegisteredEvent(this, chefOrderDTO));

    }

    public void registerNew(@NotNull Order order) throws DuplicatedEntityException {

        //Validation
        if(orderDAO.existsByOrderReference(order.getOrderReference())){
            throw new DuplicatedEntityException("There is already an order with this reference");
        }
        orderDAO.save(order);

        //converts order to orderDTO
        ChefOrderDTO chefOrderDTO = OrderConverter.order2chefOrderDTO(order);

        //reports event
        applicationEventPublisher.publishEvent(new ChefOrderRegisteredEvent(this, chefOrderDTO));

    }

    public void updateOrder(Order order) throws NonExistentEntityException {
        if (!orderDAO.existsByOrderReference(order.getOrderReference())) {
            throw new NonExistentEntityException("There is no order with id = " + order.getId());
        }
        orderDAO.save(order);
    }                    //---------------> criar evento de update order?

    public void deleteOrder(ChefOrderID id) throws NonExistentEntityException {
        if(!orderDAO.existsById(id)){
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
        orderDAO.delete(orderDAO.findById(id).get());
    }
}
