package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.dao;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierOrderID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.OrderStatus;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.SupplierOrder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
public class SupplierOrderDAO extends AbstractStockDAO {

    public static String SUPPLIERSORDERS_COLLECTION = "suppliersorders";

    private MongoCollection<SupplierOrder> suppliersOrdersCollection;

    public SupplierOrderDAO() {
        suppliersOrdersCollection = db.getCollection(SUPPLIERSORDERS_COLLECTION, SupplierOrder.class);
    }

    public List<SupplierOrder> getSuppliersOrders() {
        List<SupplierOrder> supplierOrders = new ArrayList<>();
        suppliersOrdersCollection.find().sort(Sorts.ascending()).iterator().forEachRemaining(supplierOrders::add);
        return supplierOrders;
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

    public List<SupplierOrder> getAllSuppliersOrdersByOrderStatus(OrderStatus orderStatus) {
        List<SupplierOrder> supplierOrders = new ArrayList<>();
        // TODO
        return supplierOrders;
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
