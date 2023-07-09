package hexlet.code.formatters;

import java.util.Map;

public class FormatStylish {

    public static String formattingStylishMap(Map<String, Object> inMap, String key) {
        String status = (String) inMap.get("statusDiff");
        StringBuilder stringMap = new StringBuilder();
        Object value = inMap.get("oldValue");
        stringMap.append("\n" + "  ");
        switch (status) {
            case "deleted":
                stringMap.append("- ");
                break;
            case "added":
                stringMap.append("+ ");
                value = inMap.get("newValue");
                break;
            case "unchanged":
                stringMap.append("  ");
                break;
            case "changed":
                stringMap.append(formattingStylishTwoStringMap(inMap, key));
                return stringMap.toString();
            default:
                throw new IllegalStateException("Unexpected status diff: " + status);
        }
        stringMap.append(key + formattingValue(value));
        return stringMap.toString();
    }

    public static String formattingValue(Object value) {
        if (value == null) {
            return ": null";
        } else {
            return ": " + value.toString();
        }
    }

    public static String formattingStylishTwoStringMap(Map<String, Object> mapDiff, String keyDiff) {
        StringBuilder rezult = new StringBuilder();
        String key = keyDiff;
        Object oldvalue = mapDiff.get("oldValue");
        Object newvalue = mapDiff.get("newValue");
        rezult.append("- " + key);
        rezult.append(formattingValue(oldvalue));
        rezult.append("\n" + "  " + "+ " + key);
        rezult.append(formattingValue(newvalue));
        return rezult.toString();
    }
}
