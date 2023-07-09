package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class CreateDataMap {
    public static List<Map<String, Object>> generateListDiff(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> resultListDiff = new ArrayList<>();
        TreeSet<String> keysAllDiffs = new TreeSet<>(map1.keySet());
        keysAllDiffs.addAll(map2.keySet());
        for (String key: keysAllDiffs) {
            Map<String, Object> oneRecord = CreateDataMap.diffOneKey(key, map1, map2);
            resultListDiff.add(oneRecord);
        }
        return resultListDiff;
    }
    public static Map<String, Object> diffOneKey(String keyMap, Map<String, Object> map1, Map<String, Object> map2) {
        String statusElement = "unchanged";
        Object oldValue = null;
        Object newValue = null;
        if (map1.keySet().contains(keyMap) && (!map2.keySet().contains(keyMap))) {
            statusElement = "deleted";
            oldValue = map1.get(keyMap);
        }
        if (!map1.keySet().contains(keyMap) && (map2.keySet().contains(keyMap))) {
            statusElement = "added";
            newValue = map2.get(keyMap);
        }
        if (map1.keySet().contains(keyMap) && (map2.keySet().contains(keyMap))) {
            oldValue = map1.get(keyMap);
            newValue = map2.get(keyMap);
            if ((oldValue == null) && (newValue == null)) {
                statusElement = "unchanged";
            } else {
                if ((oldValue == null) || (newValue == null) || (!oldValue.equals(newValue))) {
                    statusElement = "changed";
                }
            }
        }
        return elementDiff(keyMap, statusElement, oldValue, newValue);
    }

    public static Map<String, Object> elementDiff(String keyMap, String status, Object oldElement, Object newElement) {
        LinkedHashMap recordDiff = new LinkedHashMap();
        recordDiff.put("statusDiff", status);
        recordDiff.put("oldValue", oldElement);
        recordDiff.put("newValue", newElement);
        LinkedHashMap recordList = new LinkedHashMap();
        recordList.put(keyMap, recordDiff);
        return recordList;
    }
}
