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
            Status status = Status.valueOf(map.get(DifferKey.STATUS).toString());
            switch (status) {
                case UPDATED -> {
                    result.add("  - " + name + ": " + oldValue);
                    result.add("  + " + name + ": " + newValue);
                }
                case ADD -> result.add("  + " + name + ": " + newValue);
                case SAME -> result.add("    " + name + ": " + oldValue);
                case REMOVED -> result.add("  - " + name + ": " + oldValue);
            }
        });
        result.add("}");
        return result.toString();
    }
}
