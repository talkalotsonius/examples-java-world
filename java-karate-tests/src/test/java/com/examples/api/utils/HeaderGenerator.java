package com.examples.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Objects;

public class HeaderGenerator {

    private static final String CLASSPATH = "src/test/resources/payloads/headers/custom/";

    private final String customHeaderPath;
    private String requestContentType = "application/json";
    private String responseContentType = "application/json";

    private final Map<String,String> header = new LinkedHashMap<>();

    public HeaderGenerator(String customHeaderPath) {
        this.customHeaderPath = CLASSPATH + customHeaderPath;
    }

    public HeaderGenerator(String customHeaderPath,
                           String requestContentType,
                           String responseContentType) {
        this.customHeaderPath = CLASSPATH + customHeaderPath;
        this.requestContentType = requestContentType;
        this.responseContentType = responseContentType;
    }


    public String getJsonObjectString(String keysToRemove) {
        buildHeader();
        removeKeys(keysToRemove);

        return new JSONObject(header).toString();
    }

    public JSONObject getJsonObject(String keysToRemove) {
        buildHeader();
        removeKeys(keysToRemove);

        return new JSONObject(header);
    }

    private void removeKeys(String keysToRemove) {
        if (!Objects.equals(keysToRemove, "") &&
            !Objects.equals(keysToRemove, null)) {
            List<String> removableKeysList = Arrays.asList(keysToRemove.split(",[ ]*"));

            for (String key : removableKeysList) {
                header.remove(key);
            }
        }
    }

    private void buildHeader() {
        setContentTypeHeader();
        loadCustomHeaders();
    }

    private void setContentTypeHeader() {
        header.put("content-type", requestContentType);
        header.put("accept", responseContentType);
    }

    private void loadCustomHeaders() {
        LinkedHashMap result;

        try{
            result = new ObjectMapper().readValue(new File(customHeaderPath), LinkedHashMap.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        header.putAll(result);
    }

}
