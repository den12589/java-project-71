package hexlet.code.formatters;

import hexlet.code.DifferKey;
import hexlet.code.Status;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class StylishFormatter {
    public static String format(List<Map<DifferKey, Object>> differences) {
        StringJoiner result = new StringJoiner("\n");
        result.add("{");
        differences.forEach(map -> {
            String name = map.get(DifferKey.NAME).toString();
            String oldValue = Objects.toString(map.get(DifferKey.OLD_VALUE), "null");
            String newValue = Objects.toString(map.get(DifferKey.NEW_VALUE), "null");
            switch (map.get(DifferKey.STATUS)) {
                case Status.ADD -> result.add("  + " + name + ": " + newValue);
                case Status.SAME -> result.add("    " + name + ": " + oldValue);
                case Status.REMOVED -> result.add("  - " + name + ": " + oldValue);
                case Status.UPDATED -> {
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
