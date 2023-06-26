package hexlet.code;

import hexlet.code.formatters.FormatJson;
import hexlet.code.formatters.FormatPlain;
import hexlet.code.formatters.FormatStylish;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
//    public static String formatting(String format, List<ElementOfDiff> resultList) {
    public static String formatting(String format, List<Map<String, Object>> resultList) throws IOException {
        switch (format) {
            case "stylish" -> {
                return FormatStylish.formattingStylish(resultList);
            }
            case "plain" -> {
                return FormatPlain.formattingPlain(resultList);
            }
            case "json", "JSON" -> {
                return FormatJson.formattingJson(resultList);
            }
            default -> {
                throw new IllegalStateException("Unexpected format: " + format);
            }
        }
    }
}
