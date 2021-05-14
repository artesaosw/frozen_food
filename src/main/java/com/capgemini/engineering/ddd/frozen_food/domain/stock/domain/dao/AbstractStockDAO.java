package com.capgemini.engineering.ddd.frozen_food.domain.stock.domain.dao;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
public abstract class AbstractStockDAO {

    protected final String FROZEN_FOOD;
    protected MongoDatabase db;
    protected MongoClient mongoClient;
    @Value("${spring.mongodb.uri}")
    private String connectionString;

    protected AbstractStockDAO(MongoClient mongoClient, String databaseName) {
        this.mongoClient = mongoClient;
        FROZEN_FOOD = databaseName;
        this.db = this.mongoClient.getDatabase(FROZEN_FOOD);
    }

    public ObjectId generateObjectId() {
        return new ObjectId();
    }

    public Map<String, Object> getConfiguration() {
        ConnectionString connString = new ConnectionString(connectionString);

        Map<String, Object> configuration = new HashMap<>();

        configuration.put("pool_size", connString.getMaxConnectionPoolSize());
        configuration.put(
                "wtimeout",
                this.mongoClient
                        .getDatabase(FROZEN_FOOD)
                        .getWriteConcern()
                        .getWTimeout(TimeUnit.MILLISECONDS));

        return configuration;
    }
}
