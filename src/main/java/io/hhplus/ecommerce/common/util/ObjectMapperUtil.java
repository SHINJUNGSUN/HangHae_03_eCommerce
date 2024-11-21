package io.hhplus.ecommerce.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectMapperUtil {

    public static String toJson(Object object) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObject(String value, Class<T> object) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(value, object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
