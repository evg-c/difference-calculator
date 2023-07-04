package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;

public class CreateDataMap {
    public static Map<String, Object> generateDiff(String keyMap, Map<String, Object> map1, Map<String, Object> map2) {
        // генерируем одну запись различий для конкретного ключа, передаваемого в качестве параметра (или две записи)
        String statusElement = "unchanged";
        Object oldValue = null;
        Object newValue = null;
        if (map1.keySet().contains(keyMap) && (!map2.keySet().contains(keyMap))) {
            // есть в первом файле и нет во втором, то есть ключ удален: -
            statusElement = "deleted";
            oldValue = map1.get(keyMap);
        }
        if (!map1.keySet().contains(keyMap) && (map2.keySet().contains(keyMap))) {
            // нет в первом файле, есть во втором? то есть ключ добавлен: +
            statusElement = "added";
            newValue = map2.get(keyMap);
        }
        if (map1.keySet().contains(keyMap) && (map2.keySet().contains(keyMap))) {
            // ключ есть и в первом, и во втором файле, надо сравнивать значения
            return diffTwo(keyMap, map1, map2);
        }
        return elementDiff(keyMap, statusElement, oldValue, newValue);
    }

    public static Map<String, Object> diffTwo(String keyMap, Map<String, Object> map1, Map<String, Object> map2) {
        // ключ есть и в первом, и во втором файле, надо сравнивать значения
        String statusElement = "unchanged";
        Object valueMap = map1.get(keyMap);
        Object valueMap2 = map2.get(keyMap);
        // необходимо проверить значения обоих ключей на null
        if ((valueMap == null) || (valueMap2 == null) || (!valueMap.equals(valueMap2))) {
            statusElement = "changed";
        } else {
            statusElement = "unchanged";
        }
        return elementDiff(keyMap, statusElement, valueMap, valueMap2);
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
