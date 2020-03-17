package ru.aplana.objpajes;

import java.util.HashMap;
import java.util.Map;

public class TestData {
    public static Map<String, Object> map = new HashMap();

    public static Object getItem(String key){
        return map.get(key);
    }

    public static void putItem(String key, Object item){
        map.put(key, item);
    }
}
