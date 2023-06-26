package hexlet.code.formatters;

//import hexlet.code.ElementOfDiff;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FormatStylish {

//    public static String formattingStylish(List<ElementOfDiff> inList) {
//        if (inList.isEmpty()) {
//            return "";
//        }
//        StringBuilder rezult = new StringBuilder();
//        rezult.append("{\n");
//        for (ElementOfDiff record: inList) {
//            String key = record.getKeyDiff();
//            String status = record.getStatusDiff();
//            Object value;
//            rezult.append("  ");
//            switch (status) {
//                case "deleted":
//                    rezult.append("- ");
//                    value = record.getOldValue();
//                    break;
//                case "added":
//                    rezult.append("+ ");
//                    value = record.getNewValue();
//                    break;
//                case "unchanged":
//                    rezult.append("  ");
//                    value = record.getOldValue();
//                    break;
//                case "changed":
//                    // надо вывести две строки
//                    rezult.append(formattingStylishTwoString(record));
//                    continue;
//                default:
//                    throw new IllegalStateException("Unexpected status diff: " + status);
//            }
//            rezult.append(key);
//            if (value == null) {
//                rezult.append(": null"  + "\n");
//            } else {
//                rezult.append(": " + value.toString() + "\n");
//            }
//        }
//        rezult.append("}");
//        return rezult.toString();
//    }

    public static String formattingStylish(List<Map<String, Object>> inList) {
        if (inList.isEmpty()) {
            return "";
        }
        StringBuilder rezult = new StringBuilder();
        rezult.append("{\n");
        for (Map<String, Object> recordList : inList) {
            // сначала итерируемся по List - одна строчка List это Map
            for (Map.Entry<String, Object> recordMap : recordList.entrySet()) {
                // здесь итерируемся по строчке Map
                String key = recordMap.getKey();
                // достаем вложенный Map
                Map<String, Object> nestedMap = new LinkedHashMap<>();
                nestedMap = (Map<String, Object>) recordMap.getValue();
                rezult.append(formattingStylishMap(nestedMap, key));
            }
        }
        rezult.append("}");
        return rezult.toString();
    }

//            String status = recordList.getStatusDiff();
//            Object value;
//            rezult.append("  ");
//            switch (status) {
//                case "deleted":
//                    rezult.append("- ");
//                    value = recordList.getOldValue();
//                    break;
//                case "added":
//                    rezult.append("+ ");
//                    value = recordList.getNewValue();
//                    break;
//                case "unchanged":
//                    rezult.append("  ");
//                    value = recordList.getOldValue();
//                    break;
//                case "changed":
//                    // надо вывести две строки
//                    rezult.append(formattingStylishTwoString(recordList));
//                    continue;
//                default:
//                    throw new IllegalStateException("Unexpected status diff: " + status);
//            }
//            rezult.append(key);
//            if (value == null) {
//                rezult.append(": null"  + "\n");
//            } else {
//                rezult.append(": " + value.toString() + "\n");
//            }
//        }
//        rezult.append("}");
//        return rezult.toString();
//    }

    public static String formattingStylishMap(Map<String, Object> inMap, String key) {
        String status = (String) inMap.get("statusDiff");
        StringBuilder stringMap = new StringBuilder();
        Object value;
        stringMap.append("  ");
        switch (status) {
            case "deleted":
                stringMap.append("- ");
                value = inMap.get("oldValue");
                break;
            case "added":
                stringMap.append("+ ");
                value = inMap.get("newValue");
                break;
            case "unchanged":
                stringMap.append("  ");
                value = inMap.get("oldValue");
                break;
            case "changed":
                // надо вывести две строки
                stringMap.append(formattingStylishTwoStringMap(inMap, key));
                //continue;
                return stringMap.toString();
            default:
                throw new IllegalStateException("Unexpected status diff: " + status);
        }
        stringMap.append(key);
        if (value == null) {
            stringMap.append(": null"  + "\n");
        } else {
            stringMap.append(": " + value.toString() + "\n");
        }
//        stringMap.append("}");
        return stringMap.toString();
    }

//    public static String formattingStylishTwoString(ElementOfDiff element) {
//        StringBuilder rezult = new StringBuilder();
//        String key = element.getKeyDiff();
//        String status = element.getStatusDiff();
//        rezult.append("- " + key);
//        if (element.getOldValue() == null) {
//            rezult.append(": null"  + "\n");
//        } else {
//            rezult.append(": " + element.getOldValue().toString() + "\n");
//        }
//        rezult.append("  " + "+ " + key);
//        if (element.getNewValue() == null) {
//            rezult.append(": null"  + "\n");
//        } else {
//            rezult.append(": " + element.getNewValue().toString() + "\n");
//        }
//        return rezult.toString();
//    }

    public static String formattingStylishTwoStringMap(Map<String, Object> mapDiff, String keyDiff) {
        StringBuilder rezult = new StringBuilder();
        String key = keyDiff;
        String status = "changed";
        Object oldvalue = (Object) mapDiff.get("oldValue");
        Object newvalue = (Object) mapDiff.get("newValue");
        rezult.append("- " + key);
        if (oldvalue == null) {
            rezult.append(": null"  + "\n");
        } else {
            rezult.append(": " + oldvalue.toString() + "\n");
        }
        rezult.append("  " + "+ " + key);
        if (newvalue == null) {
            rezult.append(": null"  + "\n");
        } else {
            rezult.append(": " + newvalue.toString() + "\n");
        }
        return rezult.toString();
    }
}
