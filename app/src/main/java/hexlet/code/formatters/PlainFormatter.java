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
            Status status = Status.valueOf(map.get(DifferKey.STATUS).toString());
            switch (status) {
                case ADD -> result.add("Property '" + name + "' was added with value: " + newValue);
                case REMOVED -> result.add("Property '" + name + "' was removed");
                case UPDATED -> result.add("Property '" + name + "' was updated. From " + oldValue + " to " + newValue);
                default -> {
                } // only for checkstyle
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
