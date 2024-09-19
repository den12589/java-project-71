package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class PlainFormatter {
    public static String format(List<Map<String, Object>> compareList) {
        StringJoiner result = new StringJoiner("\n");
        compareList.forEach(map -> {
            String oldValue = createValueString(map.get("OLD VALUE"));
            String newValue = createValueString(map.get("NEW VALUE"));
            String name = map.get("NAME").toString();
            switch (map.get("STATUS").toString()) {
                case "SAME" -> {
                }
                case "ADD" -> result.add("Property '" + name + "' was added with value: " + newValue);
                case "UPDATE" ->
                        result.add("Property '" + name + "' was updated. From " + oldValue + " to " + newValue);
                case "REMOVED" -> result.add("Property '" + name + "' was removed");
                default -> throw new RuntimeException("Status isn't correct");
            }
        });
        return result.toString();
    }

    private static String createValueString(Object o) {
        StringBuilder result = new StringBuilder();
        if (Objects.isNull(o)) {
            result.append("null");
        }
        if (isRecommendedType(o)) {
            result.append("[complex value]");
        }
        if (isString(o)) {
            result.append("'").append(o).append("'");
        }
        if (isBooleanOrInteger(o)) {
            result.append(o);
        }
        return result.toString();
    }

    private static boolean isBooleanOrInteger(Object o) {
        return (o instanceof Boolean) || (o instanceof Integer);
    }

    private static boolean isString(Object o) {
        return o instanceof String;
    }

    private static boolean isRecommendedType(Object o) {
        return !isString(o) && !isBooleanOrInteger(o) && !Objects.isNull(o);
    }
}
