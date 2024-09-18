import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;
    private static final String BEGIN_PATH_EXPECTED = "src/test/resources/expected_results/";
    private static final String BEGIN_PATH_FILES = "src/test/resources/input_files/";

    @BeforeAll
    static void setup() throws IOException {
        Path pathStylish = Paths.get(BEGIN_PATH_EXPECTED + "resultForStylish.txt").toAbsolutePath().normalize();
        Path pathPlain = Paths.get(BEGIN_PATH_EXPECTED + "resultForPlain.txt").toAbsolutePath().normalize();
        Path pathJson = Paths.get(BEGIN_PATH_EXPECTED + "resultForJson.json").toAbsolutePath().normalize();
        expectedStylish = Files.readString(pathStylish).trim();
        expectedPlain = Files.readString(pathPlain).trim();
        expectedJson = Files.readString(pathJson).trim();
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    void testGenerateDefault(String fileFormat) throws IOException {
        var file1 = BEGIN_PATH_FILES + "file1." + fileFormat;
        var file2 = BEGIN_PATH_FILES + "file2." + fileFormat;
        assertEquals(expectedStylish, Differ.generate(file1, file2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    void testGenerateStylish(String fileFormat) throws IOException {
        var file1 = BEGIN_PATH_FILES + "file1." + fileFormat;
        var file2 = BEGIN_PATH_FILES + "file2." + fileFormat;
        assertEquals(expectedStylish, Differ.generate(file1, file2, "stylish"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    void testGeneratePlain(String fileFormat) throws IOException {
        var file1 = BEGIN_PATH_FILES + "file1." + fileFormat;
        var file2 = BEGIN_PATH_FILES + "file2." + fileFormat;
        assertEquals(expectedPlain, Differ.generate(file1, file2, "plain"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    void testGenerateJson(String fileFormat) throws IOException {
        var file1 = BEGIN_PATH_FILES + "file1." + fileFormat;
        var file2 = BEGIN_PATH_FILES + "file2." + fileFormat;
        assertEquals(expectedJson, Differ.generate(file1, file2, "json"));
    }
}
