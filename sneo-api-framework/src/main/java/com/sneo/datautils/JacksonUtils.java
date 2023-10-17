package com.sneo.datautils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JacksonUtils class -
 * deserializeJson - deserializes json to POJO object
 * convertPOJOToDataProviderObject - converts POJO to DataProvider Object
 * convertTwoDMapToPOJO
 *
 * @author ikumar
 */
public class JacksonUtils {

    public static <T> T deserializeJson(String jsonString, Class<T> T) {
        //InputStream is = JacksonUtils.class.getClassLoader().getResourceAsStream(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonString, T);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String serializeJson(Object pojo) {
        //InputStream is = JacksonUtils.class.getClassLoader().getResourceAsStream(fileName);
        ObjectMapper mapper = new ObjectMapper();

        String json;
        try {
            json = mapper.writeValueAsString(pojo);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    public static Object[][] convertPOJOToDataProviderObject(Object[] objects) {
        Object[][] returnObject = new Object[objects.length][1];
        for (int i = 0; i < objects.length; i++) {
            returnObject[i][0] = objects[i];
        }
        return returnObject;
    }

    public static <T> T convertTwoDMapToPOJO(Map<String, String>[][] map, Class<T> T) throws IOException {
        ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        return mapper.convertValue(map, T);
    }

    public static Map<String, String> convertJsonToMap(String jsonString) {
        ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        Map<String, String> map = new HashMap<>();
        try {
            map = mapper.readValue(jsonString, new TypeReference<HashMap<String, String>>() {
            });
        }catch (Exception e){
            e.printStackTrace();

        }
        return map;
    }



}
