package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.dao;

import com.capgemini.engineering.ddd.frozen_food.domain._shared.IngredientID;
import com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.entity.Ingredient;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IngredientDAO extends AbstractStockDAO {

    public static String INGREDIENT_COLLECTION = "ingredient";

    private MongoCollection<Ingredient> ingredientsCollection;

    public IngredientDAO(MongoClient mongoClient, @Value("${spring.mongodb.database}") String databaseName) {
        super(mongoClient, databaseName);
        ingredientsCollection = db.getCollection(INGREDIENT_COLLECTION, Ingredient.class);
    }

    public Ingredient getIngredientByName(String name) {
        Ingredient ingredient = null;
        // TODO
        return ingredient;
    }

    public Ingredient getIngredientById(IngredientID ingredientID) {
        Ingredient ingredient = null;
        // TODO
        return ingredient;
    }

    public List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientsCollection.find().sort(Sorts.ascending()).iterator().forEachRemaining(ingredients::add);
        return ingredients;
    }

    public long getIngredientsCount() {
        return this.ingredientsCollection.countDocuments();
    }

    public boolean addIngredient(Ingredient ingredient) {
        ingredientsCollection.insertOne(ingredient);
        return true;
    }

    public boolean deleteIngredient(Ingredient ingredient) {
        ingredientsCollection.deleteOne((Bson) ingredient);
        return true;
    }

    public boolean updateIngredient(Ingredient ingredient) {
        // TODO
        // ingredientsCollection.updateOne(ingredient);
        return true;
    }
}
