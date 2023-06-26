package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FormatJson {
    public static String formattingJson(List<Map<String, Object>> inList) throws JsonProcessingException {
        if (inList.isEmpty()) {
            return "";
        }
        StringBuilder rezult = new StringBuilder();
        ObjectMapper mapperJson = new ObjectMapper();
        for (Map<String, Object> recordList : inList) {
            // сначала итерируемся по List - одна строчка List это Map
            for (Map.Entry<String, Object> recordMap : recordList.entrySet()) {
                // здесь итерируемся по строчке Map
//                String key = recordMap.getKey();
//                // достаем вложенный Map
//                Map<String, Object> nestedMap = new LinkedHashMap<>();
//                nestedMap = (Map<String, Object>) recordMap.getValue();
                String diffAsJson = mapperJson.writeValueAsString(recordMap);
//                rezult.append(formattingJsonMap(nestedMap, key));
                rezult.append(diffAsJson + "\n");
            }
        }
        return rezult.toString();
    }

    public static String formattingJsonMap(Map<String, Object> inMap, String key) throws JsonProcessingException {
        ObjectMapper mapperJson = new ObjectMapper();
        String diffAsJson = mapperJson.writeValueAsString(inMap);
        return diffAsJson;
    }
}
