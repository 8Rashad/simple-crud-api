package com.example.jpa.model.enums;

import com.fasterxml.jackson.databind.ObjectMapper;

public enum ObjectMapperSingleton {
    INSTANCE;

    private final ObjectMapper objectMapper;

    ObjectMapperSingleton() {
        objectMapper = new ObjectMapper();
        // Configure your ObjectMapper settings here, if needed.
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
