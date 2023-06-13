import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class DifferTest {

    @Test
    public void testGenerateDiff() throws IOException {
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
}
