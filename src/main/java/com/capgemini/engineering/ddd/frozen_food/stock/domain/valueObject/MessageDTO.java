package com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "message")
public class MessageDTO {

    @JacksonXmlProperty(localName = "message")
    private String message;

    public MessageDTO(String message) {
        this.message = message;
    }

    public MessageDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("message").append(message);
        return sb.toString();
    }
}
