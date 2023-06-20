package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        if (!checkingParameters(filepath1, filepath2)) {
            return "";
        }
        // формируем мапы из входных json-файлов
        Map<String, Object> mapFromFile1 = Parser.readFileToMap(filepath1);
        Map<String, Object> mapFromFile2 = Parser.readFileToMap(filepath2);
        // формируем общий список ключей
        TreeSet<String> keys = new TreeSet<>(mapFromFile1.keySet());
        keys.addAll(mapFromFile2.keySet());
        // формируем мапу различий
        List<ElementOfDiff> resultListDiff = new ArrayList<>();
        for (String key: keys) {
            ElementOfDiff oneRecord = (ElementOfDiff) generateDiff(key, mapFromFile1, mapFromFile2);
            resultListDiff.add(oneRecord);
        }
        String resultString = Formatter.formatting(format, resultListDiff);
        return resultString;
    }

    public static boolean checkParameterFile(String file) throws IOException {
        // проверка файла, переданного в параметре
        if (file.isEmpty()) {
            System.out.println("Путь к файлу пуст");
            return false;
        }
        Path pathForFile = Paths.get(file);
        if (!Files.exists(pathForFile)) {
            System.out.println("Файл не существует");
            return false;
        }
        if (!Files.isReadable(pathForFile)) {
            System.out.println("Файл недоступен для чтения");
            return false;
        }
        if (Files.isDirectory(pathForFile)) {
            System.out.println("Это каталог, а не файл!");
            return false;
        }
        return true;
    }

    public static ElementOfDiff generateDiff(String keyMap, Map<String, Object> map1, Map<String, Object> map2) {
        // генерируем одну запись различий для конкретного ключа, передаваемого в качестве параметра (или две записи)
        String statusElement = "unchanged";
        if (map1.keySet().contains(keyMap) && (!map2.keySet().contains(keyMap))) {
            // есть в первом файле и нет во втором, то есть ключ удален: -
            statusElement = "deleted";
        }
        if (!map1.keySet().contains(keyMap) && (map2.keySet().contains(keyMap))) {
            // нет в первом файле, есть во втором? то есть ключ добавлен: +
            statusElement = "added";
        }
        if (map1.keySet().contains(keyMap) && (map2.keySet().contains(keyMap))) {
            // ключ есть и в первом, и во втором файле, надо сравнивать значения
            return genDiffTwoFiles(keyMap, map1, map2);
        }
        ElementOfDiff recordDiff = new ElementOfDiff(keyMap, statusElement, map1.get(keyMap), map2.get(keyMap));
        return recordDiff;
    }

    public static ElementOfDiff genDiffTwoFiles(String keyMap, Map<String, Object> map1, Map<String, Object> map2) {
        String statusElement = "unchanged";
        Object valueMap = map1.get(keyMap);
        // ключ есть и в первом, и во втором файле, надо сравнивать значения
        valueMap = map1.get(keyMap);
        Object valueMap2 = map2.get(keyMap);
        // необходимо проверить значения обоих ключей на null
        if ((valueMap == null) || (valueMap2 == null) || (!valueMap.equals(valueMap2))) {
            statusElement = "changed";
        } else {
            statusElement = "unchanged";
        }
        ElementOfDiff recordDiff = new ElementOfDiff(keyMap, statusElement, map1.get(keyMap), map2.get(keyMap));
        return recordDiff;
    }

    public static String getExtensionFile(String pathFile) {
        Path pathForFile = Paths.get(pathFile);
        File inFile = pathForFile.toFile();
        String nameFile = inFile.getName();
        int i = nameFile.lastIndexOf(".");
        String extension = i > 0 ? nameFile.substring(i + 1) : "";
        return extension;
    }

    public static boolean checkingParameters(String file1, String file2) throws IOException {
        // сначала проверим параметры как файлы по отдельности (что они существуют и доступны для чтения)
        if (!checkParameterFile(file1) || (!checkParameterFile(file2))) {
            return false;
        }
        // проверим, что сравниваем разные файлы
        if (file1.equals(file2)) {
            System.out.println("В обоих параметрах один и тот же файл!");
            return false;
        }
        // узнать расширение файла
        String extensionFile1 = getExtensionFile(file1);
        String extensionFile2 = getExtensionFile(file2);
        // проверим, что файлы имеют одинаковое расширение
        if (!extensionFile1.equals(extensionFile2)) {
            System.out.println("Файлы с разными расширениями (разного типа) !");
            return false;
        }
        return true;
    }
}
