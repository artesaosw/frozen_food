package com.capgemini.engineering.ddd.frozen_food.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import javax.annotation.PostConstruct;

//MongoDB can't accept stuff with dots, so we replace them with commas.
@Configuration
public class MongoDotConfiguration {

    @Autowired
    private MappingMongoConverter mongoConverter;

    @PostConstruct
    public void setUpMongoEscapeCharacterConversion() {
        mongoConverter.setMapKeyDotReplacement(",");
    }
}
