package com.capgemini.engineering.ddd.frozen_food.stock.domain.valueObject;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "error")
public class ErrorDTO {

    @JacksonXmlProperty(localName = "message")
    private String error;

    public ErrorDTO(Exception e) {
        this.error = e.getMessage();
    }

    public ErrorDTO() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("error").append(error);
        return sb.toString();
    }
}
