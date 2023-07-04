package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        if (!checkingParameters(filepath1, filepath2)) {
            return "";
        }
        String string1 = Files.readString(Path.of(filepath1));
        String string2 = Files.readString(Path.of(filepath2));
        String extension1 = Differ.getExtensionFile(filepath1);
        String extension2 = Differ.getExtensionFile(filepath2);
        // формируем мапы из входных json-файлов
//        Map<String, Object> map1 = Parser.readFileToMap(filepath1);
//        Map<String, Object> map2 = Parser.readFileToMap(filepath2);
        Map<String, Object> map1 = Parser.readStringToMap(string1, extension1);
        Map<String, Object> map2 = Parser.readStringToMap(string2, extension2);
        // формируем общий список ключей
        TreeSet<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());
        // формируем мапу различий
        List<Map<String, Object>> resultListDiff = new ArrayList<>();
        for (String key: keys) {
            Map<String, Object> oneRecord = CreateDataMap.generateDiff(key, map1, map2);
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
        String string1 = Files.readString(Path.of(file1));
        String string2 = Files.readString(Path.of(file2));
        if (string1.isEmpty()) {
            System.out.println("Файл" + file1 + " пустой!");
            return false;
        }
        if (string2.isEmpty()) {
            System.out.println("Файл" + file2 + " пустой!");
            return false;
        }
        return true;
    }
}
