package com.capgemini.engineering.ddd.frozen_food._shared.utils;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "error")
public class Error {

    @JacksonXmlProperty(localName = "message")
    private String error;

    public Error(Exception e) {
        this.error = e.getMessage();
    }

    public Error() {
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
