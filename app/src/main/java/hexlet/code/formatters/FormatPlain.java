package hexlet.code.formatters;

import java.util.Map;

public class FormatPlain {

    public static String formattingPlainMap(Map<String, Object> inMap, String key) {
        String status = (String) inMap.get("statusDiff");
        if (status.equals("unchanged")) {
            return "";
        }
        StringBuilder stringMap = new StringBuilder();
        Object oldvalue = (Object) inMap.get("oldValue");
        Object newvalue = (Object) inMap.get("newValue");
        //stringMap.append("\n");
        switch (status) {
            case ("deleted"):
                stringMap.append("Property '" + key + "' was removed");
                break;
            case ("added"):
                stringMap.append("Property '" + key + "' was added with value: ");
                stringMap.append(computingValue(newvalue));
                break;
            case ("changed"):
                stringMap.append("Property '" + key + "' was updated. From ");
                stringMap.append(computingValue(oldvalue) + " to " + computingValue(newvalue));
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
