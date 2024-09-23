package hexlet.code.formatters;

import hexlet.code.DifferKey;
import hexlet.code.Status;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class PlainFormatter {
    public static String format(List<Map<DifferKey, Object>> differences) {
        StringJoiner result = new StringJoiner("\n");
        differences.forEach(map -> {
            String name = map.get(DifferKey.NAME).toString();
            String oldValue = createValueString(map.get(DifferKey.OLD_VALUE));
            String newValue = createValueString(map.get(DifferKey.NEW_VALUE));
            switch (map.get(DifferKey.STATUS)) {
                case Status.ADD -> result.add("Property '" + name + "' was added with value: " + newValue);
                case Status.SAME -> {
                }
                case Status.REMOVED -> result.add("Property '" + name + "' was removed");
                case Status.UPDATED -> result.add("Property '" + name + "' was updated. From " + oldValue + " to " + newValue);
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
