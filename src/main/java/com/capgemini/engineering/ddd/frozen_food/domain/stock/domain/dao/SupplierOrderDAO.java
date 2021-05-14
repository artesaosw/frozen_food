package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.dao;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.SupplierOrder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SupplierOrderDAO extends AbstractStockDAO {

    public static String SUPPLIERSORDERS_COLLECTION = "suppliersorders";

    private MongoCollection<SupplierOrder> suppliersOrdersCollection;

    public SupplierOrderDAO(MongoClient mongoClient, @Value("${spring.mongodb.database}") String databaseName) {
        super(mongoClient, databaseName);
        suppliersOrdersCollection = db.getCollection(SUPPLIERSORDERS_COLLECTION, SupplierOrder.class);
    }

    public SupplierOrder getSupplierOrderById(SupplierOrderID supplierOrderID) {
        SupplierOrder supplierOrder = null;
        // TODO
        return supplierOrder;
    }

    public SupplierOrder getSupplierOrderByReference(String reference) {
        SupplierOrder supplierOrder = null;
        // TODO
        return supplierOrder;
    }

    public List<SupplierOrder> getDeliveredSuppliersOrders() {
        List<SupplierOrder> suppliersOrders = new ArrayList<>();
        // TODO
        return suppliersOrders;
    }

    public List<SupplierOrder> getUndeliveredSuppliersOrders() {
        List<SupplierOrder> suppliersOrders = new ArrayList<>();
        // TODO
        return suppliersOrders;
    }

    public List<SupplierOrder> getCanceledSuppliersOrders() {
        List<SupplierOrder> suppliersOrders = new ArrayList<>();
        // TODO
        return suppliersOrders;
    }

    public boolean addSupplierOrder(SupplierOrder supplierOrder) {
        suppliersOrdersCollection.insertOne(supplierOrder);
        return true;
    }

    public boolean deleteSupplierOrder(SupplierOrder supplierOrder) {
        suppliersOrdersCollection.deleteOne((Bson) supplierOrder);
        return true;
    }

    public boolean updateSupplierOrder(SupplierOrder supplierOrder) {
        // TODO
        // suppliersOrdersCollection.updateOne(supplierOrder);
        return true;
    }
}
