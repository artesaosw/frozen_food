package com.capgemini.engineering.ddd.frozen_food.stock.domain.entity;

import com.capgemini.engineering.ddd.frozen_food._shared.Unit;
import com.capgemini.engineering.ddd.frozen_food._shared.id.ChefOrderID;
import com.capgemini.engineering.ddd.frozen_food._shared.id.Identificator;
import com.capgemini.engineering.ddd.frozen_food._shared.id.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject.NIF;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SupplierTest {

    private Supplier supplier;
    private NIF nif;
    private ChefOrder chefOrder;
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Ingredient ingredient3;

    Map<IngredientID, Integer> orderID = new HashMap();

    Map<Ingredient, Integer> order = new HashMap();

    List<Ingredient> ingredients = new ArrayList();

    public SupplierTest() {
        nif = new NIF("257899898");
        supplier = new Supplier("Nome Test", nif,"cpf@cpf.com","987654321");

        ingredient1 = new Ingredient("apple", Unit.KG);
        ingredient2 = new Ingredient("onion", Unit.KG);
        ingredient3 = new Ingredient("pineapple", Unit.KG);

        orderID.put(ingredient1.getId(), 100);
        orderID.put(ingredient2.getId(), 50);
        orderID.put(ingredient3.getId(), 20);

        order.put(ingredient1, 100);
        order.put(ingredient2, 50);
        order.put(ingredient3, 20);

        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);

//        chefOrder = new ChefOrder("BUY1", orderID);
    }

    @Test
    void testJsonFormatSupplier() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(supplier);

        System.out.println(supplier.toString());

        System.out.println(json);
    }

    @Test
    void testJsonFormatIngredient() throws  JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(ingredient1);

        System.out.println(json);
    }

    @Test
    void testJsonFormatListIngredient() throws  JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(ingredients);

        System.out.println(json);
    }

    @Test
    void testJsonFormatMapIngredientID() throws  JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(orderID);

        System.out.println(json);
    }

    @Test
    void testJsonFormatMapIngredient() throws  JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(order);

        System.out.println(json);
    }

    @Test
    void testJsonFormatIngredientOrder() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(chefOrder);

        System.out.println(json);
    }


}