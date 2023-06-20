package hexlet.code.formatters;

import hexlet.code.ElementOfDiff;
import java.util.List;

public class FormatPlain {
    public static String formattingPlain(List<ElementOfDiff> inList) {
        if (inList.isEmpty()) {
            return "";
        }
        StringBuilder rezult = new StringBuilder();
        for (var record: inList) {
            String status = record.getStatusDiff();
            if (status.equals("unchanged")) {
                continue;
            }
            String key = record.getKeyDiff();
            Object oldvalue = record.getOldValue();
            Object newvalue = record.getNewValue();
            switch (status) {
                case ("deleted"):
                    rezult.append("Property '" + key + "' was removed\n");
                    break;
                case ("added"):
                    rezult.append("Property '" + key + "' was added with value: ");
                    rezult.append(computingValue(newvalue) + "\n");
                    break;
                case ("changed"):
                    rezult.append("Property '" + key + "' was updated. From ");
                    rezult.append(computingValue(oldvalue) + " to " + computingValue(newvalue) + "\n");
                    break;
                default:
                    throw new IllegalStateException("Unexpected status diff: " + status);
            }
        }
        return rezult.toString();
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
