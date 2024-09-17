package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatOutput) throws IOException {
        String content1 = readFile(filePath1);
        String content2 = readFile(filePath2);

        String format1 = getFileType(filePath1);
        String format2 = getFileType(filePath2);

        Map<String, Object> parseFile1 = Parser.parse(content1, format1);
        Map<String, Object> parseFile2 = Parser.parse(content2, format2);

        List<Map<String, Object>> result = Comparator.compare(parseFile1, parseFile2);

       return format(result, formatOutput);
    }

    private static String readFile(String filePath) throws IOException {
        var path = Paths.get(filePath);
        return Files.readString(path).trim();
    }

    private static String getFileType(String filePath) {
        String[] strings = filePath.split("\\.");
        return strings[strings.length - 1];
    }

    private static String format(List<Map<String, Object>> result, String formatOutput) {
        return switch (formatOutput) {
            case "stylish" -> StylishFormatter.format(result);
            case "plain" -> PlainFormatter.format(result);
            default -> throw new RuntimeException("Unsupported format");
        };
    }
}
