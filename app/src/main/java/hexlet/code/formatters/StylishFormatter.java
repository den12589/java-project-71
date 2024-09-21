package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

import static hexlet.code.Statuses.ADD;
import static hexlet.code.Statuses.SAME;
import static hexlet.code.Statuses.REMOVED;
import static hexlet.code.Statuses.UPDATED;

public class StylishFormatter {
    public static String format(List<Map<String, Object>> differences) {
        StringJoiner result = new StringJoiner("\n");
        result.add("{");
        differences.forEach(map -> {
            String name = map.get("NAME").toString();
            String oldValue = Objects.toString(map.get("OLD VALUE"), "null");
            String newValue = Objects.toString(map.get("NEW VALUE"), "null");
            switch (map.get("STATUS")) {
                case ADD -> result.add("  + " + name + ": " + newValue);
                case SAME -> result.add("    " + name + ": " + oldValue);
                case REMOVED -> result.add("  - " + name + ": " + oldValue);
                case UPDATED -> {
                    result.add("  - " + name + ": " + oldValue);
                    result.add("  + " + name + ": " + newValue);
                }
                default -> throw new RuntimeException("Status isn't correct");
            }
        });
        result.add("}");
        return result.toString();
    }
}
