package com.saucedemo.apps.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonUtils {
    private static final ThreadLocal<Map<String, Object>> jsonData =
            ThreadLocal.withInitial(HashMap::new);

    public static void loadDeviceProfile(String filename) throws IOException {
        String filePath = "src/test/resources/device-profiles/" + filename;
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filePath);

        if (!file.exists()) {
            throw new IOException("No profile found: " + filePath);
        }

        Map<String, Object> map = mapper.readValue(file, Map.class);

        jsonData.get().clear();
        jsonData.get().putAll(map);
    }

    public static String getProfileValue(String key) {
        Map<String, Object> object = jsonData.get();
        Object value;

        if (key.contains(".")) {
            String[] partialKeys = key.split("\\."); // partialKeys[0] = device     partialKeys[1] = name
            Map<String, Object> nestedJsonObject =
                    (Map<String, Object>) object.get(partialKeys[0]);
            value = nestedJsonObject != null ? nestedJsonObject.get(partialKeys[1]) : null;
        } else {
            value = object.get(key);
        }

        return value != null ? value.toString() : null;
    }
}
