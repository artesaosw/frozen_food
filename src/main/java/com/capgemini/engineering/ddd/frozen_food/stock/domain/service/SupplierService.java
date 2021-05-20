package com.capgemini.engineering.ddd.frozen_food.stock.domain.service;

import com.capgemini.engineering.ddd.frozen_food.__metadata.DomainServices;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.DuplicatedEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.exception.NonExistentEntityException;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.SupplierID;
import com.capgemini.engineering.ddd.frozen_food.stock.Stock;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.NIF;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.entity.Supplier;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.repository.Suppliers;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.SupplierDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class SupplierService implements DomainServices {

    @Autowired
    SupplierDAO supplierDAO;

    private Suppliers suppliers() {
        return Stock.suppliers();
    }

    public Supplier getSupplierBySupplierID(@NotNull SupplierID id) {
        if (!supplierDAO.existsBySupplierID(id)) {
            throw new DuplicatedEntityException("There is no supplier with SupplierID = " + id);
        }
        return supplierDAO.findBySupplierID(id);
    }

    public Supplier getSupplierByName(@NotBlank String name) {
        if (!supplierDAO.existsByName(name)) {
            throw new DuplicatedEntityException("There is no supplier with name = " + name);
        }
        return supplierDAO.findByName(name);
    }

    public Supplier getSupplierByNif(@NotBlank NIF nif) {
        if (!supplierDAO.existsByNif(nif)) {
            throw new DuplicatedEntityException("There is no supplier with NIF = " + nif);
        }
        return supplierDAO.findByNif(nif);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierDAO.findAll();
    }

    public void registerNewSupplier(@NotBlank String name, @NotNull NIF nif) {
        if (supplierDAO.existsByNif(nif)) {
            throw new DuplicatedEntityException("Already exists another supplier with the same NIF.");
        }
        Supplier supplier = new Supplier(name, nif);
        supplierDAO.save(supplier);
    }

    public void registerNewSupplier(@NotNull Supplier supplier) {
        if (supplierDAO.existsBySupplierID(supplier.id())) {
            throw new DuplicatedEntityException("Already exists another supplier with the same id.");
        }
        if (supplierDAO.existsByNif(supplier.getNif())) {
            throw new DuplicatedEntityException("Already exists another supplier with the same NIF.");
        }
        supplierDAO.save(supplier);
    }

    public void updateSupplier(@NotNull Supplier supplier) {
        if (!supplierDAO.existsBySupplierID(supplier.id())) {
            throw new NonExistentEntityException("There is no supplier with id = " + supplier.id());
        }
        supplierDAO.save(supplier);
    }

    public void deleteSupplier(@NotNull SupplierID id) {
        if (!supplierDAO.existsBySupplierID(id)) {
            throw new NonExistentEntityException("There is no supplier with id = " + id);
        }
        Supplier supplier = supplierDAO.findBySupplierID(id);
        supplierDAO.delete(supplier);
    }
}
