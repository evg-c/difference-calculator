package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.IOException;
import java.util.Map;

public class Parser {

    public static Map<String, Object> readStringToMap(String stringForMap, String extension) throws IOException {
        switch (extension) {
            case ("json"), ("JSON"):
                ObjectMapper mapperJson = new ObjectMapper();
                return mapperJson.readValue(stringForMap, Map.class);
            case ("yml"), ("YML"):
                ObjectMapper mapperYml = new YAMLMapper();
                return mapperYml.readValue(stringForMap, Map.class);
            default:
                throw new IllegalStateException("Unexpected value: " + extension);
        }
    }
}
