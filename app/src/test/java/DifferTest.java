import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DifferTest {

    @Test
    public void testGenerateDiffFromJsonOld() throws IOException {
        StringBuilder str = new StringBuilder();
        str.append("{\n");
        str.append("  - follow: false\n");
        str.append("    host: hexlet.io\n");
        str.append("  - proxy: 123.234.53.22\n");
        str.append("  - timeout: 50\n");
        str.append("  + timeout: 20\n");
        str.append("  + verbose: true\n");
        str.append("}");
        String diffFromApp = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json");
        assertEquals(str.toString(), diffFromApp);
    }

    @Test
    public void testGenerateDiffFromJsonNew() throws IOException {
        String resultDiff;
        resultDiff = Files.readString(Path.of("src/test/resources/result1.txt"));
        String diffFromApp = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json");
        assertEquals(resultDiff, diffFromApp);
    }
    @Test
    public void testGenerateDiffFromYmlOld() throws IOException {
        StringBuilder str = new StringBuilder();
        str.append("{\n");
        str.append("  - follow: false\n");
        str.append("    host: hexlet.io\n");
        str.append("  - proxy: 123.234.53.22\n");
        str.append("  - timeout: 50\n");
        str.append("  + timeout: 20\n");
        str.append("  + verbose: true\n");
        str.append("}");
        String diffFromApp = Differ.generate("src/test/resources/file1.yml", "src/test/resources/file2.yml");
        assertEquals(str.toString(), diffFromApp);
    }

    @Test
    public void testGenerateDiffFromYmlNew() throws IOException {
        String resultDiff;
        resultDiff = Files.readString(Path.of("src/test/resources/result1.txt"));
        String diffFromApp = Differ.generate("src/test/resources/file1.yml", "src/test/resources/file2.yml");
        assertEquals(resultDiff, diffFromApp);
    }

    @Test
    public void testGenerateDiffFromJsonNested() throws IOException {
        String correctDiff;
        correctDiff = Files.readString(Path.of("src/test/resources/result2.txt"));
        String diffFromApp = Differ.generate("src/test/resources/file3.json", "src/test/resources/file4.json");
        assertEquals(correctDiff, diffFromApp);
    }

    @Test
    public void testGenerateDiffFromYmlNested() throws IOException {
        String correctDiff;
        correctDiff = Files.readString(Path.of("src/test/resources/result2.txt"));
        String diffFromApp = Differ.generate("src/test/resources/file5.yml", "src/test/resources/file6.yml");
        assertEquals(correctDiff, diffFromApp);
    }

    @Test
    public void testGenerateDiffFromJsonNewPLain() throws IOException {
        String resultDiff;
        resultDiff = Files.readString(Path.of("src/test/resources/result4plain"));
        String diffFromApp;
        diffFromApp = Differ.generate("src/test/resources/file3.json", "src/test/resources/file4.json", "plain");
        assertEquals(resultDiff, diffFromApp);
    }

    @Test
    public void testGenerateDiffFromYmlNestedPlain() throws IOException {
        String correctDiff;
        correctDiff = Files.readString(Path.of("src/test/resources/result4plain"));
        String diffFromApp = Differ.generate("src/test/resources/file5.yml", "src/test/resources/file6.yml", "plain");
        assertEquals(correctDiff, diffFromApp);
    }
    @Test
    public void testGenerateDiffFromJsonNewToJson() throws IOException {
        String resultDiff;
        resultDiff = Files.readString(Path.of("src/test/resources/resultDiff2.json"));
        String diffFromApp;
        diffFromApp = Differ.generate("src/test/resources/file3.json", "src/test/resources/file4.json", "json");
        assertEquals(resultDiff, diffFromApp);
    }

    @Test
    public void testGenerateDiffFromYmlNestedToJson() throws IOException {
        String correctDiff;
        correctDiff = Files.readString(Path.of("src/test/resources/resultDiff2.json"));
        String diffFromApp = Differ.generate("src/test/resources/file5.yml", "src/test/resources/file6.yml", "json");
        assertEquals(correctDiff, diffFromApp);
    }
}
