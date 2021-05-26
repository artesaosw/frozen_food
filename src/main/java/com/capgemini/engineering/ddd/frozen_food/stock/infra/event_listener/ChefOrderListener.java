package com.capgemini.engineering.ddd.frozen_food.stock.infra.event_listener;

import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderIngredientDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.menu_stock.ChefOrderStatusDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.event.menu_stock.ChefOrderIngredientUpdatedEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.event.menu_stock.ChefOrderRegisteredEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.event.menu_stock.ChefOrderStatusUpdatedEvent;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ChefOrder;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.ChefOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import static com.capgemini.engineering.ddd.frozen_food.stock.domain.converter.ChefOrderConverter.chefOrderDTO2ChefOrder;
import static com.capgemini.engineering.ddd.frozen_food.stock.domain.converter.ChefOrderIngredientConverter.chefOrderIngredientDTO2ChefOrder;

@Service
public class ChefOrderListener {

    @Autowired
    ChefOrderDAO chefOrderDAO;

    @EventListener
    public void registerNewChefOrder(ChefOrderRegisteredEvent chefOrderRegisteredEvent) {
        ChefOrderDTO chefOrderDTO = chefOrderRegisteredEvent.getChefOrderDTO();
        ChefOrder chefOrder = chefOrderDTO2ChefOrder(chefOrderDTO);
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

    @EventListener
    public void updateChefOrderStatus(ChefOrderStatusUpdatedEvent chefOrderStatusUpdatedEvent) {
        ChefOrderStatusDTO chefOrderStatusDTO = chefOrderStatusUpdatedEvent.getChefOrderStatusDTO();
        ChefOrder chefOrder = chefOrderDAO.findById(chefOrderStatusDTO.getId()).get();
        chefOrder.setOrderStatus(chefOrderStatusDTO.getOrderStatus());
        chefOrderDAO.save(chefOrder);
    }

    @EventListener
    public void updateChefOrderIngredients(ChefOrderIngredientUpdatedEvent chefOrderIngredientUpdatedEvent) {
        ChefOrderIngredientDTO chefOrderIngredientDTO = chefOrderIngredientUpdatedEvent.getChefOrderIngredientDTO();
        ChefOrder chefOrder = chefOrderDAO.findById(chefOrderIngredientDTO.getId()).get();
        chefOrder.setOrders(chefOrderIngredientDTO2ChefOrder(chefOrderIngredientDTO).getOrders());
        chefOrderDAO.save(chefOrder);
    }
}
