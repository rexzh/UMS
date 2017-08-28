package com.ums.management.core.utility;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONExtension {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    public static String stringify(Object object) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert Object to JSON", e);
        }
        return json;
    }

    public static <T> T parseJson(String json, Class<T> clazz) {
        T t = null;
        try {
            t = objectMapper.readValue(json, clazz);
        } catch (IOException e) {

            throw new RuntimeException("Failed to convert JSON to Object", e);
        }
        return t;
    }

    public static <T> T parseJson(String json, TypeReference<T> template) {
        T t = null;
        try {
            t = objectMapper.readValue(json, template);
        } catch (IOException e) {

            throw new RuntimeException("Failed to convert JSON to Object", e);
        }
        return t;
    }
}
