/**
 * Picocli: gendiff
 * Specify another file name as command line parameter, e.g. '/src/test/resources/file1.json'
 * Specify another file name as command line parameter, e.g. '/src/test/resources/file2.json'
 * Specify format as command line parameter, e.g. '-f plain'
 * @author Evgeny_ch
 */
package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.util.concurrent.Callable;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import static hexlet.code.Differ.generate;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;
    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    private String format = "stylish";
//    @Option(names = {"-V", "--version"}, versionHelp = true, description = "display version info")
//    boolean versionInfoRequested;
//    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message")
//    boolean usageHelpRequested;
    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int ERROR_EXIT_CODE = 1;

    @Override
    public Integer call() throws Exception { // your business logic goes here...
        try {
            String formattedDiff = generate(filepath1, filepath2, format);
            System.out.println(formattedDiff);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ERROR_EXIT_CODE;
        }
        return SUCCESS_EXIT_CODE;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

}
