package com.sid.moviebkg.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonUtils {

    @Autowired
    private ObjectMapper objectMapper;

    public <T> String convertObjectAsJson(T payload) throws JsonProcessingException {
        objectMapper.findAndRegisterModules();
        return objectMapper.writeValueAsString(payload);
    }

    public <T> T convertJsonToObject(String payload, Class<T> tClass) throws JsonProcessingException {
        return objectMapper.readValue(payload, tClass);
    }
}
