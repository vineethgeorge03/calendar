package com.example.calendar.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MetaDataConverter implements AttributeConverter<Map<String, Object>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> metaData) {
        String metaDataJson = null;
        try {
            metaDataJson = objectMapper.writeValueAsString(metaData);
        } catch (final JsonProcessingException e) {
            System.out.println("wriign error" + e);
        }

        return metaDataJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String metaDataJson) {
        Map<String, Object> metaData = null;
        try {
            metaData = objectMapper.readValue(metaDataJson,
                    new TypeReference<HashMap<String, Object>>() {});
        } catch (final IOException e) {
            System.out.println("JSON reading error" + e);
        }

        return metaData;
    }
}
