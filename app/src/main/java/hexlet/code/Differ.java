package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        // сначала проверим параметры как файлы по отдельности (что они существуют и доступны для чтения)
        if (!checkParameterFile(filepath1) || (!checkParameterFile(filepath2))) return "";
        // теперь проверим, что сравниваем разные файлы
        if (filepath1.equals(filepath2)) {
            System.out.println("В обоих параметрах один и тот же файл!");
            return "";
        }
        // если сюда дошли, значит с файлами все в порядке
        // формируем мапы из входных json-файлов
        Map<String, Object> mapFromJson1 = readJsonFile(filepath1);
        Map<String, Object> mapFromJson2 = readJsonFile(filepath2);
//        System.out.println("map1: " + mapFromJson1);
//        System.out.println("map2: " + mapFromJson2);
        // формируем общий список ключей
        TreeSet<String> keys = new TreeSet<>(mapFromJson1.keySet());
        keys.addAll(mapFromJson2.keySet());
        //System.out.println("Все ключи: " + keys);
        // формируем мапу различий
        Map<String, Object> resultMapDiff = new LinkedHashMap<>();
        for (String key: keys) {
            Map<String, Object> oneRecord = new LinkedHashMap<>();
            oneRecord = generateDiff(key, mapFromJson1, mapFromJson2);
            resultMapDiff.putAll(oneRecord);
        }
        //System.out.println(resultMapDiff);
        String resultString = toString(resultMapDiff);
        //System.out.println(resultString);
        return resultString;
    }

    public static boolean checkParameterFile(String pathfile) throws IOException {
        // проверка файла, переданного в параметре
        if (pathfile.isEmpty()) {
            System.out.println("Путь к файлу пуст");
            return false;
        }
        Path pathForFile = Paths.get(pathfile);
//        System.out.println("Имя файла: " + pathForFile.getFileName());
//        System.out.println("Путь к файлу: " + pathForFile);
//        System.out.println("Абсолютный путь к файлу: " + pathForFile.toAbsolutePath());
        if (Files.exists(pathForFile)) {
            //System.out.println("Файл существует");
        }
        else {
            System.out.println("Файл не существует");
            return false;
        }
        if (Files.isReadable(pathForFile)) {
            //System.out.println("Файл доступен для чтения");
        }
        else {
            System.out.println("Файл недоступен для чтения");
            return false;
        }
        if (Files.isRegularFile(pathForFile)) {
            //System.out.println("Это обычный файл");
        }
        else {
            System.out.println("Это не обычный файл");
        }
        if (Files.isDirectory(pathForFile)) {
            System.out.println("Это каталог, а не файл!");
            return false;
        }
        return true;
    }

    public static Map<String, Object> readJsonFile(String filepath) throws IOException {
        // читаем файл и формируем из его данных структуру Map
        Path pathForFile = Paths.get(filepath);
        File inFile = pathForFile.toFile();
        ObjectMapper mapperJson = new ObjectMapper();
        return mapperJson.readValue(inFile, Map.class);
    }

    public static Map<String, Object> generateDiff(String keyMap, Map<String, Object> map1, Map<String, Object> map2) {
        // генерируем одну запись различий для конкретного ключа, передаваемого в качестве параметра
        boolean flagOneRecord = true;
        Map<String, Object> diffMap = new LinkedHashMap<>();
        Object valueMap = map1.get(keyMap);;
        String newKeyMap = "";
        if (map1.keySet().contains(keyMap) && (!map2.keySet().contains(keyMap))) {
            // есть в первом файле и нет во втором, то есть ключ удален: -
            newKeyMap = "- " + keyMap;
            valueMap = map1.get(keyMap);
        }
        if (!map1.keySet().contains(keyMap) && (map2.keySet().contains(keyMap))) {
            // нет в первом файле, есть во втором? то есть ключ добавлен: +
            newKeyMap = "+ " + keyMap;
            valueMap = map2.get(keyMap);
        }
        if (map1.keySet().contains(keyMap) && (map2.keySet().contains(keyMap))) {
            // ключ есть и в первом, и во втором файле
            valueMap = map1.get(keyMap);
            Object valueMap2 = map2.get(keyMap);
            if (valueMap.equals(valueMap2)) {
                // значений в первом и втором совпадают, добавляем одну строку без изменений
                newKeyMap = "  " + keyMap;
            } else {
                // значения в первом и втором не совпадают, добавляем две строки
                flagOneRecord = false;
            }
        }
        if (flagOneRecord) {
            diffMap.put(newKeyMap, valueMap);
        } else {
            String key1 = "- " + keyMap;
            Object value1 = map1.get(keyMap);
            String key2 = "+ " + keyMap;
            Object value2 = map2.get(keyMap);
            diffMap.put(key1, value1);
            diffMap.put(key2, value2);
        }
        return diffMap;
    }


    public static String toString(Map<String, Object> inMap) {
        if (inMap.isEmpty()) return "";
        StringBuilder rezult = new StringBuilder();
        rezult.append("{\n");
        for (Map.Entry<String, Object> record: inMap.entrySet()) {
            String key = record.getKey();
            Object value = record.getValue();
            rezult.append(" " + key);
            rezult.append(": " + value.toString() + "\n");
        }
        rezult.append("}");
        return rezult.toString();
    }
}
