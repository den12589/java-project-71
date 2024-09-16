package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "GenDiffer", mixinStandardHelpOptions = true, version = "checksum 4.0")
public class App implements Callable {
    @Parameters(index = "0", paramLabel = "filePath1", description = "path to first file")
    private String file1;
    @Parameters(index = "1", paramLabel = "filePath2", description = "path to second file")
    private String file2;
    @Option(
            names = {"-f", "--format"},
            paramLabel = "format",
            defaultValue = "stylish",
            description = "output format [default: stylish]")
    private String format;

    @Override
    public Integer call() throws Exception {
        System.out.println(Differ.generate(file1, file2, format));
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
