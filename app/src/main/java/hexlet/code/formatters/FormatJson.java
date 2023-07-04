package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class FormatJson {
    public static String formattingJsonMap(Map.Entry<String, Object> inMap) throws JsonProcessingException {
        ObjectMapper mapperJson = new ObjectMapper();
        String diffAsJson = mapperJson.writeValueAsString(inMap);
        return diffAsJson;
    }
}
