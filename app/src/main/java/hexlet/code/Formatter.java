package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.FormatJson;
import hexlet.code.formatters.FormatPlain;
import hexlet.code.formatters.FormatStylish;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Formatter {

    public static String formatting(String format, List<Map<String, Object>> resultList) throws IOException {
        if (resultList.isEmpty()) {
            return "";
        }
        StringBuilder rezult = new StringBuilder();
        if (format.equals("stylish")) {
            rezult.append("{");
        }
        for (Map<String, Object> recordList : resultList) {
            for (Map.Entry<String, Object> recordMap : recordList.entrySet()) {
                String key = recordMap.getKey();
                Map<String, Object> nestedMap = new LinkedHashMap<>();
                nestedMap = (Map<String, Object>) recordMap.getValue();
                String line = formatLine(format, nestedMap, recordMap, key);
                String full = !format.equals("stylish") && !rezult.isEmpty() && !line.isEmpty() ? "\n" + line : line;
                rezult.append(full);
            }
        }
        if (format.equals("stylish")) {
            rezult.append("\n" + "}");
        }
        return rezult.toString();
    }

    public static String
        formatLine(String format, Map<String, Object> innerMap, Map.Entry<String, Object> extMap, String key)
            throws JsonProcessingException {
        switch (format) {
            case "stylish" -> {
                return FormatStylish.formattingStylishMap(innerMap, key);
            }
            case "plain" -> {
                return FormatPlain.formattingPlainMap(innerMap, key);
            }
            case "json", "JSON" -> {
                return FormatJson.formattingJsonMap(extMap);
            }
            default -> {
                throw new IllegalStateException("Unexpected format: " + format);
            }
        }
    }
}
