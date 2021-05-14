package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.dao;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.ProductionOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.ProductionOrder;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.SupplierOrder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductionOrderDAO extends AbstractStockDAO {

    public static String PRODUCTIONORDERS_COLLECTION = "productionorders";

    private MongoCollection<ProductionOrder> productionOrdersCollection;

    public ProductionOrderDAO(MongoClient mongoClient, @Value("${spring.mongodb.database}") String databaseName) {
        super(mongoClient, databaseName);
        productionOrdersCollection = db.getCollection(PRODUCTIONORDERS_COLLECTION, ProductionOrder.class);
    }

    public ProductionOrder getProductionOrderById(ProductionOrderID productionOrderID) {
        ProductionOrder productionOrder = null;
        // TODO
        return productionOrder;
    }

    public ProductionOrder getProductionOrderByReference(String reference) {
        ProductionOrder productionOrder = null;
        // TODO
        return productionOrder;
    }

    public List<ProductionOrder> getDeliveredProductionOrders() {
        List<ProductionOrder> productionOrders = new ArrayList<>();
        // TODO
        return productionOrders;
    }

    public List<ProductionOrder> getUndeliveredProductionOrders() {
        List<ProductionOrder> productionOrders = new ArrayList<>();
        // TODO
        return productionOrders;
    }

    public List<ProductionOrder> getConceledProductionOrders() {
        List<ProductionOrder> productionOrders = new ArrayList<>();
        // TODO
        return productionOrders;
    }

    public boolean addProductionOrder(ProductionOrder productionOrder) {
        productionOrdersCollection.insertOne(productionOrder);
        return true;
    }

    public boolean deleteProductionOrder(ProductionOrder productionOrder) {
        productionOrdersCollection.deleteOne((Bson) productionOrder);
        return true;
    }

    public boolean updateProductionOrder(ProductionOrder productionOrder) {
        // TODO
        // productionOrdersCollection.updateOne(productionOrder);
        return true;
    }
}
