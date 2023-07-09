import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Differ2Test {
    @Test
    public void testGenerateDiffFromJson() throws IOException {
        String diffExpected = dataFixture("result1.txt");
        String diffFromApp = Differ.generate(fixture("file1.json"), fixture("file2.json"));
        assertEquals(diffExpected, diffFromApp);
    }

    @Test
    public void testGenerateDiffFromYml() throws IOException {
        String diffExpected = dataFixture("result1.txt");
        String diffFromApp = Differ.generate(fixture("file1.yml"), fixture("file2.yml"));
        assertEquals(diffExpected, diffFromApp);
    }

    @Test
    public void testGenerateDiffFromJsonNested() throws IOException {
        String diffExpected = dataFixture("result2.txt");
        String diffFromApp = Differ.generate(fixture("file3.json"), fixture("file4.json"));
        assertEquals(diffExpected, diffFromApp);
    }

    @Test
    public void testGenerateDiffFromYmlNested() throws IOException {
        String diffExpected = dataFixture("result2.txt");
        String diffFromApp = Differ.generate(fixture("file5.yml"), fixture("file6.yml"));
        assertEquals(diffExpected, diffFromApp);
    }

    @Test
    public void testGenerateDiffFromJsonPLain() throws IOException {
        String diffExpected = dataFixture("result4plain");
        String diffFromApp;
        diffFromApp = Differ.generate(fixture("file3.json"), fixture("file4.json"), "plain");
        assertEquals(diffExpected, diffFromApp);
    }

    @Test
    public void testGenerateDiffFromYmlPlain() throws IOException {
        String diffExpected = dataFixture("result4plain");
        String diffFromApp;
        diffFromApp = Differ.generate(fixture("file5.yml"), fixture("file6.yml"), "plain");
        assertEquals(diffExpected, diffFromApp);
    }
    @Test
    public void testGenerateDiffFromJsonToJson() throws IOException {
        String diffExpected = dataFixture("resultDiff2.json");
        String diffFromApp;
        diffFromApp = Differ.generate(fixture("file3.json"), fixture("file4.json"), "json");
        assertEquals(diffExpected, diffFromApp);
    }

    @Test
    public void testGenerateDiffFromYmlToJson() throws IOException {
        String correctDiff = dataFixture("resultDiff2.json");
        String diffFromApp;
        diffFromApp = Differ.generate(fixture("file5.yml"), fixture("file6.yml"), "json");
        assertEquals(correctDiff, diffFromApp);
    }

    @Test
    public void testExistFile() throws IOException {
        String expected = "";
        String diffFromApp = Differ.generate(fixture("file10.json"), fixture("file2.json"));
        assertEquals(expected, diffFromApp);
    }

    @Test
    public void testEmptyNameFile() throws IOException {
        String expected = "";
        String diffFromApp = Differ.generate("", fixture("file2.json"));
        assertEquals(expected, diffFromApp);
    }

    @Test
    public void testDifferentFiles() throws IOException {
        String expected = "";
        String diffFromApp = Differ.generate(fixture("file2.json"), fixture("file2.json"));
        assertEquals(expected, diffFromApp);
    }

    @Test
    public void testDifferentExtensionsFiles() throws IOException {
        String expected = "";
        String diffFromApp = Differ.generate(fixture("file2.yml"), fixture("file2.json"));
        assertEquals(expected, diffFromApp);
    }

    @Test
    public void testUnknownExtensionsFiles() throws IOException {
        String expected = "";
        String diffFromApp = Differ.generate(fixture("result2.txt"), fixture("file2.json"));
        assertEquals(expected, diffFromApp);
    }

    @Test
    public void testEmptyContentFile() throws IOException {
        String expected = "";
        String diffFromApp = Differ.generate(fixture("file20.json"), fixture("file21.json"));
        assertEquals(expected, diffFromApp);
    }

    @Test
    public void testUnknownFormat() throws IOException {
        String expected = "";
        String diffFromApp;
        diffFromApp = Differ.generate(fixture("file1.json"), fixture("file2.json"), "lala");
        assertEquals(expected, diffFromApp);
    }

    public static String fixture(String filename) {
        return "src/test/resources/" + filename;
    }

    public static String dataFixture(String filename) throws IOException {
        return Files.readString(Path.of(fixture(filename)));
    }

}
