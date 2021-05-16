package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.dao;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.ChefOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.ChefOrder;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.ProductionOrder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChefOrderDAO extends AbstractStockDAO {

    public static String CHEFORDERS_COLLECTION = "cheforders";

    private MongoCollection<ChefOrder> chefOrdersCollection;

    public ChefOrderDAO(MongoClient mongoClient, @Value("${spring.mongodb.database}") String databaseName) {
        super(mongoClient, databaseName);
        chefOrdersCollection = db.getCollection(CHEFORDERS_COLLECTION, ChefOrder.class);
    }

    public List<ChefOrder> getChefOrders() {
        List<ChefOrder> chefOrders = new ArrayList<>();
        chefOrdersCollection.find().sort(Sorts.ascending()).iterator().forEachRemaining(chefOrders::add);
        return chefOrders;
    }

    public ChefOrder getChefOrderById(ChefOrderID chefOrderID) {
        ChefOrder chefOrder = null;
        // TODO
        return chefOrder;
    }

    public ChefOrder getChefOrderByReference(ChefOrderID chefOrderID) {
        ChefOrder chefOrder = null;
        // TODO
        return chefOrder;
    }

    public List<ChefOrder> getAllChefOrdersByOrderStatus(OrderStatus orderStatus) {
        List<ChefOrder> chefOrders = new ArrayList<>();
        // TODO
        return chefOrders;
    }

    public boolean addChefOrder(ChefOrder chefOrder) {
        chefOrdersCollection.insertOne(chefOrder);
        return true;
    }

    public boolean deleteChefOrder(ChefOrder chefOrder) {
        chefOrdersCollection.deleteOne((Bson) chefOrder);
        return true;
    }

    public boolean updateChefOrder(ChefOrder chefOrder) {
        // TODO
        // chefOrdersCollection.updateOne(chefOrder);
        return true;
    }
}
