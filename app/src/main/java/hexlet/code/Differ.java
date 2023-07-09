package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        if (!checkingParameters(filepath1, filepath2, format)) {
            return "";
        }
        String string1 = Files.readString(Path.of(filepath1));
        String string2 = Files.readString(Path.of(filepath2));
        String extension1 = Differ.getExtensionFile(filepath1);
        String extension2 = Differ.getExtensionFile(filepath2);
        Map<String, Object> map1 = Parser.readStringToMap(string1, extension1);
        Map<String, Object> map2 = Parser.readStringToMap(string2, extension2);
        List<Map<String, Object>> resultListDiff;
        resultListDiff = CreateDataMap.generateListDiff(map1, map2);
        String resultString = Formatter.formatting(format, resultListDiff);
        return resultString;
    }

    public static boolean checkParameterFile(String file) throws IOException {
        boolean resultCheck = true;
        if (file.isEmpty()) {
            System.out.println("Путь к файлу " + file + " не указан");
            resultCheck = false;
        }
        Path pathForFile = Paths.get(file);
        if (resultCheck && !Files.exists(pathForFile)) {
            System.out.println("Файл " + file + " не существует");
            resultCheck = false;
        }
        if (resultCheck && !Files.isReadable(pathForFile)) {
            System.out.println("Файл " + file + " недоступен для чтения");
            resultCheck = false;
        }
        if (resultCheck && Files.isDirectory(pathForFile)) {
            System.out.println(file + " это каталог, а не файл!");
            resultCheck = false;
        }
        String contentFile = resultCheck ? Files.readString(pathForFile) : "";
        if (resultCheck && contentFile.isEmpty()) {
            System.out.println("Содержимое файла " + file + " пустое!");
            resultCheck = false;
        }
        return resultCheck;
    }

    public static String getExtensionFile(String pathFile) {
        Path pathForFile = Paths.get(pathFile);
        File inFile = pathForFile.toFile();
        String nameFile = inFile.getName();
        int i = nameFile.lastIndexOf(".");
        String extension = i > 0 ? nameFile.substring(i + 1) : "";
        return extension;
    }

    public static boolean checkingParameters(String file1, String file2, String format) throws IOException {
        boolean resultCheck = true;
        if (!checkParameterFile(file1) || (!checkParameterFile(file2))) {
            resultCheck = false;
        }
        if (resultCheck && file1.equals(file2)) {
            System.out.println("В обоих параметрах один и тот же файл!");
            resultCheck = false;
        }
        String extensionFile1 = resultCheck ? getExtensionFile(file1) : "";
        String extensionFile2 = resultCheck ? getExtensionFile(file2) : "";
        if (resultCheck && !extensionFile1.equals(extensionFile2)) {
            System.out.println("Файлы с разными расширениями (разного типа) !");
            resultCheck = false;
        }
        if (resultCheck && (!format.equals("stylish") && !format.equals("plain") && !format.equals("json"))) {
            System.out.println("Неизвестное значение параметра формата: " + format);
            resultCheck = false;
        }
        return resultCheck;
    }
}
