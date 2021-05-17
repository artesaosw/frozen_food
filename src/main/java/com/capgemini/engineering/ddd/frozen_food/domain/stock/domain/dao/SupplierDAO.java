package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.dao;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.NIF;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.Supplier;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
public class SupplierDAO extends AbstractStockDAO {

    public static String SUPPLIER_COLLECTION = "supplier";

    private MongoCollection<Supplier> suppliersCollection;

    public SupplierDAO() {
        suppliersCollection = db.getCollection(SUPPLIER_COLLECTION, Supplier.class);
    }

    public Supplier getSupplierById(SupplierID supplierID) {
        Supplier supplier = null;
        // TODO
        return supplier;
    }

    public Supplier getSupplierByName(String name) {
        Supplier supplier = null;
        // TODO
        return supplier;
    }

    public Supplier getSupplierByNIF(NIF nif) {
        Supplier supplier = null;
        // TODO
        return supplier;
    }

    public List<Supplier> getSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        suppliersCollection.find().sort(Sorts.ascending()).iterator().forEachRemaining(suppliers::add);
        return suppliers;
    }

    public long getSuppliersCount() {
        return this.suppliersCollection.countDocuments();
    }

    public boolean addSupplier(Supplier supplier) {
        suppliersCollection.insertOne(supplier);
        return true;
    }

    public boolean deleteSupplier(Supplier supplier) {
        suppliersCollection.deleteOne((Bson) supplier);
        return true;
    }

    public boolean updateSupplier(Supplier supplier) {
        // TODO
        // suppliersCollection.updateOne(supplier);
        return true;
    }
}
