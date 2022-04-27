package ir.caspco.versatile.common.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Davood Akbari - 1400
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class MapUtil {

    @SuppressWarnings("unchecked")
    public static Map<String, ?> subMap(Map<String, ?> map, String path) {
        String[] keys = path.split("\\.");

        Map<String, ?> mapTmp = map;
        for (String key : keys) {
            Object value = mapTmp.get(key);
            if (!(value instanceof Map)) {
                return Collections.emptyMap();
            }

            mapTmp = (Map<String, ?>) value;
        }

        return mapTmp;
    }

    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> flatMap(Map<String, T> mapIn) {

        Map<String, T> mapOut = new HashMap<>();

        for (String key : mapIn.keySet()) {

            T value = mapIn.get(key);
            if (!(value instanceof Map)) {

                mapOut.put(key, value);

            } else {

                flatMap(key, (Map<String, T>) value, mapOut);

            }

        }

        return mapOut;

    }

    @SuppressWarnings("unchecked")
    private static <T> void flatMap(String newKey, Map<String, T> mapIn, Map<String, T> mapOut) {

        for (String key : mapIn.keySet()) {

            String KeyTmp = newKey + "." + key;
            T value = mapIn.get(key);

            if (!(value instanceof Map)) {

                mapOut.put(KeyTmp, value);

            } else {

                flatMap(KeyTmp, (Map<String, T>) value, mapOut);

            }

        }
    }

}
