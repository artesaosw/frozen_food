package com.capgemini.engineering.ddd.frozen_food.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food._shared.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.SupplierOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.SupplierOrder;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.StockIngredientDAO;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.SupplierOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Service
@Validated
public class SupplierOrderService implements DomainServices {

    @Autowired
    SupplierOrderDAO supplierOrderDAO;

    @Autowired
    StockIngredientDAO stockIngredientDAO;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public SupplierOrder getSupplierOrderById(@NotBlank String id) {
        SupplierOrderID supplierOrderID = Identificator.newInstance(SupplierOrderID.class, id);
        if (!supplierOrderDAO.existsById(supplierOrderID)) {
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
        return supplierOrderDAO.findById(supplierOrderID).get();
    }

    public List<SupplierOrder> getAllSuppliersOrders() {
        return supplierOrderDAO.findAll();
    }

    public List<SupplierOrder> getAllSuppliersOrdersByOrderStatus(@NotNull OrderStatus orderStatus) {
        return supplierOrderDAO.findAllByOrderStatus(orderStatus);
    }

    public void registerNewSupplierOrder(@NotNull SupplierOrder supplierOrder) {
        if (supplierOrderDAO.existsById(supplierOrder.id())) {
            throw new DuplicatedEntityException("Already exists another supplier order with the same id.");
        }
        if (supplierOrderDAO.existsByOrderReference(supplierOrder.getOrderReference())) {
            throw new DuplicatedEntityException("Already exists another supplier order with the same order reference.");
        }
        supplierOrderDAO.save(supplierOrder);
    }

    public void registerNewSupplierOrder(@NotBlank String orderReference, @NotEmpty Map<String, Integer> orders, @NotNull SupplierID id, @NotNull Integer purchaseValue) {
        if (supplierOrderDAO.existsByOrderReference(orderReference)) {
            throw new DuplicatedEntityException("Already exists another order with the same order reference.");
        }
        SupplierOrder supplierOrder = new SupplierOrder(orderReference, orders, id, purchaseValue);
        supplierOrderDAO.save(supplierOrder);
    }

    public void updateSupplierOrderStatus(@NotBlank String id, @NotNull OrderStatus orderStatus) {
        SupplierOrderID supplierOrderID = Identificator.newInstance(SupplierOrderID.class, id);
        if (!supplierOrderDAO.existsById(supplierOrderID)) {
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
        SupplierOrder supplierOrder = supplierOrderDAO.findById(supplierOrderID).get();
        if (orderStatus.equals(OrderStatus.DELIVERED)) {
            if (supplierOrder.getOrderStatus().equals(OrderStatus.DELIVERED)) {
                throw new IllegalArgumentException("Order already delivered.");
            } else {
                Map<String, Integer> orderMap = supplierOrder.getOrders();
                for (Map.Entry<String, Integer> map : orderMap.entrySet()) {
                    String ingredienID = map.getKey();
                    Integer quantity = map.getValue();
                    IngredientID ingredientID = Identificator.newInstance(IngredientID.class, ingredienID);
                    Ingredient ingredient = stockIngredientDAO.findById(ingredientID).get();
                    ingredient.increaseIngredientStock(quantity);
                    stockIngredientDAO.save(ingredient);
                }
            }
        }
        supplierOrder.setOrderStatus(orderStatus);
        supplierOrderDAO.save(supplierOrder);
    }

    public void updateSupplierOrder(@NotNull SupplierOrder supplierOrder) {
        if (!supplierOrderDAO.existsById(supplierOrder.id())) {
            throw new NonExistentEntityException("There is no supplier order with id = " + supplierOrder.id());
        }
        supplierOrderDAO.save(supplierOrder);
    }

    public void deleteSupplierOrder(@NotBlank String id) {

        SupplierOrderID supplierOrderID = Identificator.newInstance(SupplierOrderID.class, id);
        if (!supplierOrderDAO.existsById(supplierOrderID)) {
            throw new NonExistentEntityException("There is no supplier order with id = " + id);
        }
        SupplierOrder supplierOrder = supplierOrderDAO.findById(supplierOrderID).get();
        supplierOrderDAO.delete(supplierOrder);
    }
}
