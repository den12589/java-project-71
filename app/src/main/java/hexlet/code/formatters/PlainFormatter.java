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

public class PlainFormatter {
    public static String format(List<Map<DifferKey, Object>> differences) {
        StringJoiner result = new StringJoiner("\n");
        differences.forEach(map -> {
            String name = map.get(NAME).toString();
            String oldValue = createValueString(map.get(OLD_VALUE));
            String newValue = createValueString(map.get(NEW_VALUE));
            switch (map.get(STATUS)) {
                case ADD -> result.add("Property '" + name + "' was added with value: " + newValue);
                case SAME -> {
                }
                case REMOVED -> result.add("Property '" + name + "' was removed");
                case UPDATED -> result.add("Property '" + name + "' was updated. From " + oldValue + " to " + newValue);
                default -> throw new RuntimeException("Status isn't correct");
            }
        });
        return result.toString();
    }

    private static String createValueString(Object o) {
        if (o instanceof List<?> || o instanceof Map<?, ?>) {
            return "[complex value]";
        }
        if (o instanceof String) {
            return "'" + o + "'";
        }
        return Objects.toString(o, "null");
    }
}
