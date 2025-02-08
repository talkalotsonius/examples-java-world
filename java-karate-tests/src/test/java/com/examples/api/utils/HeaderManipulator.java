package com.examples.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class HeaderManipulator {

    private LinkedHashMap<String,String> header;

    public HeaderManipulator(String header) {
        LinkedHashMap result;

        try {
            result = new ObjectMapper().readValue(header, LinkedHashMap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.header = result;
    }

    public String removeKeys(String keysToRemove) {
        LinkedHashMap<String,String> modifyedHeader = header;

        if (!Objects.equals(keysToRemove, "") &&
            !Objects.equals(keysToRemove, null)) {
            List<String> removableKeysList = Arrays.asList(keysToRemove.split(",[ ]*"));

            for (String key : removableKeysList) {
                modifyedHeader.remove(key);
            }
        }

        return new JSONObject(modifyedHeader).toString();
    }

    public String modifyKeyValue(String key, String value) {
        LinkedHashMap<String,String> modifyedHeader = header;
        modifyedHeader.put(key, value);

        return new JSONObject(modifyedHeader).toString();
    }
}
