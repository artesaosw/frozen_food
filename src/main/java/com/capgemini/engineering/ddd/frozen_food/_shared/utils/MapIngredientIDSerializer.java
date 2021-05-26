package com.capgemini.engineering.ddd.frozen_food._shared.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Map;

public class MapIngredientIDSerializer extends JsonSerializer<Map<?, ?>> {

    @Override
    public void serialize(Map<?, ?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartArray();
        for (Map.Entry<?, ?> entry : value.entrySet()){
            gen.writeStartObject();
            gen.writeObjectField("IngredientID", entry.getKey());
            gen.writeObjectField("Quantity", entry.getValue());
            gen.writeEndObject();
        }
        gen.writeEndArray();
    }
}
