import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;
    private static final String beginPathExpected = "src/test/resources/expected_results/";
    private static final String beginPathFiles = "src/test/resources/input_files/";

    @BeforeAll
    static void setup() throws IOException {
        expectedStylish = Files.readString(Paths.get(beginPathExpected + "resultForStylish.txt").toAbsolutePath().normalize()).trim();
        expectedPlain = Files.readString(Paths.get(beginPathExpected + "resultForPlain.txt").toAbsolutePath().normalize()).trim();
        expectedJson = Files.readString(Paths.get(beginPathExpected + "resultForJson.json").toAbsolutePath().normalize()).trim();
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    void testGenerateDefault(String fileFormat) throws IOException {
        var file1 = beginPathFiles + "file1." + fileFormat;
        var file2 = beginPathFiles + "file2." + fileFormat;
        assertEquals(expectedStylish, Differ.generate(file1, file2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    void testGenerateStylish(String fileFormat) throws IOException {
        var file1 = beginPathFiles + "file1." + fileFormat;
        var file2 = beginPathFiles + "file2." + fileFormat;
        assertEquals(expectedStylish, Differ.generate(file1, file2, "stylish"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    void testGeneratePlain(String fileFormat) throws IOException {
        var file1 = beginPathFiles + "file1." + fileFormat;
        var file2 = beginPathFiles + "file2." + fileFormat;
        assertEquals(expectedPlain, Differ.generate(file1, file2, "plain"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    void testGenerateJson(String fileFormat) throws IOException {
        var file1 = beginPathFiles + "file1." + fileFormat;
        var file2 = beginPathFiles + "file2." + fileFormat;
        assertEquals(expectedJson, Differ.generate(file1, file2, "json"));
    }
}
