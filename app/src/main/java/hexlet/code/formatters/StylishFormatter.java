package hexlet.code.formatters;

import hexlet.code.DifferKey;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

import static hexlet.code.DifferKey.NAME;
import static hexlet.code.DifferKey.STATUS;
import static hexlet.code.DifferKey.OLD_VALUE;
import static hexlet.code.DifferKey.NEW_VALUE;
import static hexlet.code.Status.ADD;
import static hexlet.code.Status.SAME;
import static hexlet.code.Status.REMOVED;
import static hexlet.code.Status.UPDATED;

public class StylishFormatter {
    public static String format(List<Map<DifferKey, Object>> differences) {
        StringJoiner result = new StringJoiner("\n");
        result.add("{");
        differences.forEach(map -> {
            String name = map.get(NAME).toString();
            String oldValue = Objects.toString(map.get(OLD_VALUE), "null");
            String newValue = Objects.toString(map.get(NEW_VALUE), "null");
            switch (map.get(STATUS)) {
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
