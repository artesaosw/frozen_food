package com.capgemini.engineering.ddd.frozen_food.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food._shared.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food._shared.dto.production_stock.ProductionOrderStatusDTO;
import com.capgemini.engineering.ddd.frozen_food._shared.event.production_stock.ProductionOrderStatusUpdatedEvent;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Ingredient;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.ProductionOrder;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.ProductionOrderDAO;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.StockIngredientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

import static com.capgemini.engineering.ddd.frozen_food.stock.domain.converter.ProductionOrderStatusConverter.productionOrder2ProductionOrderStatusDTO;

@Service
@Validated
public class ProductionOrderService {

    @Autowired
    ProductionOrderDAO productionOrderDAO;

    @Autowired
    StockIngredientDAO stockIngredientDAO;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public ProductionOrder getProductionOrderById(@NotBlank String id) {
        ProductionOrderID productionOrderID = Identificator.newInstance(ProductionOrderID.class, id);
        if (!productionOrderDAO.existsById(productionOrderID)) {
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
        return productionOrderDAO.findById(productionOrderID).get();
    }

    public List<ProductionOrder> getAllProductionOrders() {
        return productionOrderDAO.findAll();
    }

    public List<ProductionOrder> getAllProductionOrdersByOrderStatus(@NotNull OrderStatus orderStatus) {
        return productionOrderDAO.findAllByOrderStatus(orderStatus);
    }

    public void registerNewProductionOrder(@NotNull ProductionOrder productionOrder) {
        if (productionOrderDAO.existsById(productionOrder.id())) {
            // TODO Event to report problem to production domain
            throw new DuplicatedEntityException("Already exists another production order with the same id.");
        }
        if (productionOrderDAO.existsByOrderReference(productionOrder.getOrderReference())) {
            // TODO Event to report problem to production domain
            throw new DuplicatedEntityException("Already exists another production order with the same order reference.");
        }
        productionOrderDAO.save(productionOrder);
    }

    public void registerNewProductionOrder(@NotBlank String orderReference, @NotEmpty Map<String, Integer> orders) {
        if (productionOrderDAO.existsByOrderReference(orderReference)) {
            throw new DuplicatedEntityException("Already exists another order with the same order reference.");
        }
        ProductionOrder productionOrder = new ProductionOrder(orderReference, orders);
        productionOrderDAO.save(productionOrder);
    }

    public void updateProductionOrderStatus(@NotBlank String id, @NotNull OrderStatus orderStatus) {
        ProductionOrderID productionOrderID = Identificator.newInstance(ProductionOrderID.class, id);
        if (!productionOrderDAO.existsById(productionOrderID)) {
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
        ProductionOrder productionOrder = productionOrderDAO.findById(productionOrderID).get();
        if (productionOrder.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            throw new IllegalArgumentException("Order already delivered.");
        }
        if (orderStatus.equals(OrderStatus.ON_DELIVERY)) {
            if (productionOrder.getOrderStatus().equals(OrderStatus.ON_DELIVERY)) {
                throw new IllegalArgumentException("Order already on delivery.");
            } else {
                Map<String, Integer> orderMap = productionOrder.getOrders();
                orderMap.forEach((ingredienID, quantity) -> {
                    IngredientID ingredientID = Identificator.newInstance(IngredientID.class, ingredienID);
                    Ingredient ingredient = stockIngredientDAO.findById(ingredientID).get();
                    ingredient.decreaseIngredientStock(quantity);
                    stockIngredientDAO.save(ingredient);
                });
            }
        }
        productionOrder.setOrderStatus(orderStatus);
        productionOrderDAO.save(productionOrder);
        ProductionOrderStatusDTO productionOrderStatusDTO = productionOrder2ProductionOrderStatusDTO(productionOrder);
        applicationEventPublisher.publishEvent(new ProductionOrderStatusUpdatedEvent(this, productionOrderStatusDTO));
    }

    public void updateProductionOrderIngredients(@NotNull ProductionOrderID id, @NotEmpty Map<String, Integer> orders) {
        if (!productionOrderDAO.existsById(id)) {
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
        ProductionOrder productionOrder = productionOrderDAO.findById(id).get();
        if (productionOrder.getOrderStatus().equals(OrderStatus.DELIVERED)) {
            throw new IllegalArgumentException("Order already delivered.");
        }
        productionOrder.setOrders(orders);
        productionOrderDAO.save(productionOrder);
    }

    public void updateProductionOrder(@NotNull ProductionOrder productionOrder) {
        if (!productionOrderDAO.existsById(productionOrder.id())) {
            throw new NonExistentEntityException("There is no order with id = " + productionOrder.id());
        }
        productionOrderDAO.save(productionOrder);
    }

    public void deleteProductionOrder(@NotBlank String id) {
        ProductionOrderID productionOrderID = Identificator.newInstance(ProductionOrderID.class, id);
        if (!productionOrderDAO.existsById(productionOrderID)) {
            throw new NonExistentEntityException("There is no order with id = " + id);
        }
        ProductionOrder productionOrder = productionOrderDAO.findById(productionOrderID).get();
        productionOrderDAO.delete(productionOrder);
    }
}
