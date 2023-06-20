package hexlet.code.formatters;

import hexlet.code.ElementOfDiff;
import java.util.List;

public class FormatStylish {

    public static String formattingStylish(List<ElementOfDiff> inList) {
        if (inList.isEmpty()) {
            return "";
        }
        StringBuilder rezult = new StringBuilder();
        rezult.append("{\n");
        for (ElementOfDiff record: inList) {
            String key = record.getKeyDiff();
            String status = record.getStatusDiff();
            Object value;
            rezult.append("  ");
            switch (status) {
                case "deleted":
                    rezult.append("- ");
                    value = record.getOldValue();
                    break;
                case "added":
                    rezult.append("+ ");
                    value = record.getNewValue();
                    break;
                case "unchanged":
                    rezult.append("  ");
                    value = record.getOldValue();
                    break;
                case "changed":
                    // надо вывести две строки
                    rezult.append(formattingStylishTwoString(record));
                    continue;
                default:
                    throw new IllegalStateException("Unexpected status diff: " + status);
            }
            rezult.append(key);
            if (value == null) {
                rezult.append(": null"  + "\n");
            } else {
                rezult.append(": " + value.toString() + "\n");
            }
        }
        rezult.append("}");
        return rezult.toString();
    }

    public static String formattingStylishTwoString(ElementOfDiff element) {
        StringBuilder rezult = new StringBuilder();
        String key = element.getKeyDiff();
        String status = element.getStatusDiff();
        rezult.append("- " + key);
        if (element.getOldValue() == null) {
            rezult.append(": null"  + "\n");
        } else {
            rezult.append(": " + element.getOldValue().toString() + "\n");
        }
        rezult.append("  " + "+ " + key);
        if (element.getNewValue() == null) {
            rezult.append(": null"  + "\n");
        } else {
            rezult.append(": " + element.getNewValue().toString() + "\n");
        }
        return rezult.toString();
    }
}
