package com.capgemini.engineering.ddd.frozen_food.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderStatusDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.event.menu_stock.ChefOrderStatusUpdatedEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ChefOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ChefOrder;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.ChefOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

import static com.capgemini.engineering.ddd.frozen_food.stock.domain.converter.ChefOrderStatusConverter.chefOrder2ChefOrderStatusDTO;

@Service
public class ChefOrderService {

//        código para a Isabel implemetar
//        @Autowired
//        private final ApplicationEventPublisher applicationEventPublisher;

//        dentro do método
//        ChefOrderDTO chefOrderDTO = chefOrder2ChefOrderDTO(chefOrder); ** usa o converter dela;
//        em vez de chefOrder, usa o nome da entidade dela
//        applicationEventPublisher.publishEvent(new ChefOrderRegisteredEvent(this, chefOrderDTO));

//        para o ingrediente faz a mesma coisa

    @Autowired
    ChefOrderDAO chefOrderDAO;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public ChefOrder getChefOrderById(ChefOrderID id) {
        if (!chefOrderDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
        return chefOrderDAO.findById(id).get();
    }

    public List<ChefOrder> getAllChefOrders() {
        return chefOrderDAO.findAll();
    }

    public List<ChefOrder> getAllChefOrdersByOrderStatus(OrderStatus orderStatus) {
        return chefOrderDAO.findAllByOrderStatus(orderStatus);
    }

    public void registerNewChefOrder(@NotNull ChefOrder chefOrder) {
        if (chefOrderDAO.existsById(chefOrder.id())) {
            // TODO Event to report problem to menu domain
            throw new DuplicatedEntityException("Already exists another chef order with the same id.");
        }
        if (chefOrderDAO.existsByOrderReference(chefOrder.getOrderReference())) {
            // TODO Event to report problem to menu domain
            throw new DuplicatedEntityException("Already exists another chef order with the same order reference.");
        }
        chefOrderDAO.save(chefOrder);
    }

    public void registerNewChefOrder(@NotEmpty String orderReference, @NotEmpty Map<Ingredient, Integer> orders) {
        if (chefOrderDAO.existsByOrderReference(orderReference)) {
            throw new DuplicatedEntityException("Already exists another order with the same order reference.");
        }
        ChefOrder chefOrder = new ChefOrder(orderReference, orders);
        chefOrderDAO.save(chefOrder);
    }

    public void updateChefOrderStatus(@NotNull ChefOrderID id, @NotNull OrderStatus orderStatus) {
        if (!chefOrderDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
        ChefOrder chefOrder = chefOrderDAO.findById(id).get();
        if (chefOrder.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            throw new IllegalArgumentException("Order already delivered.");
        }
        chefOrder.setOrderStatus(orderStatus);
        chefOrderDAO.save(chefOrder);
        ChefOrderStatusDTO chefOrderStatusDTO = chefOrder2ChefOrderStatusDTO(chefOrder);
        applicationEventPublisher.publishEvent(new ChefOrderStatusUpdatedEvent(this, chefOrderStatusDTO));
    }

    public void updateChefOrderIngredients(@NotNull ChefOrderID id, @NotEmpty Map<Ingredient, Integer> orders) {
        if (!chefOrderDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
        ChefOrder chefOrder = chefOrderDAO.findById(id).get();
        if (chefOrder.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            throw new IllegalArgumentException("Order already delivered.");
        }
        chefOrder.setOrders(orders);
        chefOrderDAO.save(chefOrder);
    }

    public void updateChefOrder(ChefOrder chefOrder) {
        if (!chefOrderDAO.existsById(chefOrder.id())) {
            throw new NonExistentEntityException("There is no order with id = " + chefOrder.id());
        }
        chefOrderDAO.save(chefOrder);
    }

    public void deleteChefOrder(ChefOrderID id) {
        if (!chefOrderDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
        ChefOrder chefOrder = chefOrderDAO.findById(id).get();
        chefOrderDAO.delete(chefOrder);
    }
}
