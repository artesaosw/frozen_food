package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class AbstractStockDAO {

    protected static final String DATABASE_NAME = "frozen_food";
    protected MongoDatabase db;
    protected MongoClient mongoClient;
    private String uri = "mongodb+srv://frozenfood:frozenfood@cluster.iemeu.mongodb.net/test";

    protected AbstractStockDAO() {
        mongoClient = MongoClients.create(uri);
        this.db = this.mongoClient.getDatabase(DATABASE_NAME);
    }
}
