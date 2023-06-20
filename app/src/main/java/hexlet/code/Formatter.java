package hexlet.code;

import hexlet.code.formatters.FormatPlain;
import hexlet.code.formatters.FormatStylish;
import java.util.List;

public class Formatter {
    public static String formatting(String format, List<ElementOfDiff> resultList) {
        switch (format) {
            case "stylish" -> {
                return FormatStylish.formattingStylish(resultList);
            }
            case "plain" -> {
                return FormatPlain.formattingPlain(resultList);
            }
            default -> {
                throw new IllegalStateException("Unexpected format: " + format);
            }
        }
    }
}
