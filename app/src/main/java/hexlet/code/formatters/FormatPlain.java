package hexlet.code.formatters;

//import hexlet.code.ElementOfDiff;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FormatPlain {
//    public static String formattingPlain(List<ElementOfDiff> inList) {
//        if (inList.isEmpty()) {
//            return "";
//        }
//        StringBuilder rezult = new StringBuilder();
//        for (var record: inList) {
//            String status = record.getStatusDiff();
//            if (status.equals("unchanged")) {
//                continue;
//            }
//            String key = record.getKeyDiff();
//            Object oldvalue = record.getOldValue();
//            Object newvalue = record.getNewValue();
//            switch (status) {
//                case ("deleted"):
//                    rezult.append("Property '" + key + "' was removed\n");
//                    break;
//                case ("added"):
//                    rezult.append("Property '" + key + "' was added with value: ");
//                    rezult.append(computingValue(newvalue) + "\n");
//                    break;
//                case ("changed"):
//                    rezult.append("Property '" + key + "' was updated. From ");
//                    rezult.append(computingValue(oldvalue) + " to " + computingValue(newvalue) + "\n");
//                    break;
//                default:
//                    throw new IllegalStateException("Unexpected status diff: " + status);
//            }
//        }
//        return rezult.toString();
//    }

    public static String formattingPlain(List<Map<String, Object>> inList) {
        if (inList.isEmpty()) {
            return "";
        }
        StringBuilder rezult = new StringBuilder();
        for (Map<String, Object> recordList : inList) {
            // сначала итерируемся по List - одна строчка List это Map
            for (Map.Entry<String, Object> recordMap : recordList.entrySet()) {
                // здесь итерируемся по строчке Map
                String key = recordMap.getKey();
                // достаем вложенный Map
                Map<String, Object> nestedMap = new LinkedHashMap<>();
                nestedMap = (Map<String, Object>) recordMap.getValue();
                rezult.append(formattingPlainMap(nestedMap, key));
            }
        }
        return rezult.toString();
    }

    public static String formattingPlainMap(Map<String, Object> inMap, String key) {
        String status = (String) inMap.get("statusDiff");
        if (status.equals("unchanged")) {
            return "";
        }
        StringBuilder stringMap = new StringBuilder();
        Object oldvalue = (Object) inMap.get("oldValue");
        Object newvalue = (Object) inMap.get("newValue");
        switch (status) {
            case ("deleted"):
                stringMap.append("Property '" + key + "' was removed\n");
                break;
            case ("added"):
                stringMap.append("Property '" + key + "' was added with value: ");
                stringMap.append(computingValue(newvalue) + "\n");
                break;
            case ("changed"):
                stringMap.append("Property '" + key + "' was updated. From ");
                stringMap.append(computingValue(oldvalue) + " to " + computingValue(newvalue) + "\n");
                break;
            default:
                throw new IllegalStateException("Unexpected status diff: " + status);
        }
        return stringMap.toString();
    }

    public static String computingValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return ("'" + value.toString() + "'");
        }
        if ((value instanceof Integer) || (value instanceof Boolean)) {
            return (value.toString());
        } else {
            return "[complex value]";
        }
    }
}
