package com.capgemini.engineering.ddd.frozen_food.stock.domain.entity;

import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.NIF;
import com.capgemini.engineering.ddd.frozen_food.stock.infra.dao.SupplierDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class SupplierTest {

    @Autowired
    SupplierDAO supplierDAO;

    private Supplier supplier;
    private NIF nif;

    public SupplierTest() {
        nif = new NIF("257899898");
        supplier = new Supplier("Nome Test", nif,"cpf@cpf.com","987654321");
    }

    @Test
    void testToString() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(supplier);

        System.out.println(supplier.toString());

        System.out.println(json);
    }
}