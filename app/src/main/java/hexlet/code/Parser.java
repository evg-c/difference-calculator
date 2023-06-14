package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> readFileToMap(String filepath) throws IOException {
        // читаем файл и формируем из его данных структуру Map
        Path pathForFile = Paths.get(filepath);
        File inFile = pathForFile.toFile();
        String extensionFile = Differ.getExtensionFile(filepath);
        switch (extensionFile) {
            case ("json"), ("JSON"):
                ObjectMapper mapperJson = new ObjectMapper();
                return mapperJson.readValue(inFile, Map.class);
            case ("yml"), ("YML"):
                ObjectMapper mapperYml = new YAMLMapper();
                return mapperYml.readValue(inFile, Map.class);
            default:
                throw new IllegalStateException("Unexpected value: " + extensionFile);
        }
    }
}
