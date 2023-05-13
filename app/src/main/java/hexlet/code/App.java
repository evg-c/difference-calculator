/**
 * Picocli: gendiff
 * Specify another file name as command line parameter, e.g. '/usr/bin/java' or '/proc/cpuinfo'
 * Specify another file name as command line parameter, e.g. '/usr/bin/java' or '/proc/cpuinfo'
 * @author Evgeny_ch
 */
package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App {
    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
        System.out.println("Hello World!");
        //System.exit(exitCode);
    }
}
