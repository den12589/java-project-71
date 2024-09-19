package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class StylishFormatter {
    public static String format(List<Map<String, Object>> compareList) {
        StringJoiner result = new StringJoiner("\n");
        result.add("{");
        compareList.forEach(map -> {
            String name = map.get("NAME").toString();
            String oldValue = createValueString(map.get("OLD VALUE"));
            String newValue = createValueString(map.get("NEW VALUE"));
            switch (map.get("STATUS").toString()) {
                case "ADD" -> result.add("  + " + name + ": " + newValue);
                case "SAME" -> result.add("    " + name + ": " + oldValue);
                case "REMOVED" -> result.add("  - " + name + ": " + oldValue);
                case "UPDATE" -> {
                    result.add("  - " + name + ": " + oldValue);
                    result.add("  + " + name + ": " + newValue);
                }
                default -> throw new RuntimeException("Status isn't correct");
            }
        });
        result.add("}");
        return result.toString();
    }

    private static String createValueString(Object o) {
        return Objects.isNull(o) ? "null" : o.toString();
    }
}
