package com.capgemini.engineering.ddd.frozen_food.stock.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierTest {

    private Supplier supplier;
    private NIF nif;

    public SupplierTest() {
        nif = new NIF("214083209");
        supplier = new Supplier("Nome Test", nif);
    }

    @Test
    void testToString() {

    }
}