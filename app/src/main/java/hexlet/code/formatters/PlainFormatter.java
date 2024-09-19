package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class PlainFormatter {
    public static String format(List<Map<String, Object>> compareList) {
        StringJoiner result = new StringJoiner("\n");
        compareList.forEach(map -> {
            String name = map.get("NAME").toString();
            String oldValue = createValueString(map.get("OLD VALUE"));
            String newValue = createValueString(map.get("NEW VALUE"));
            switch (map.get("STATUS").toString()) {
                case "ADD" -> result.add("Property '" + name + "' was added with value: " + newValue);
                case "SAME" -> {
                }
                case "REMOVED" -> result.add("Property '" + name + "' was removed");
                case "UPDATE" ->
                        result.add("Property '" + name + "' was updated. From " + oldValue + " to " + newValue);
                default -> throw new RuntimeException("Status isn't correct");
            }
        });
        return result.toString();
    }

    private static String createValueString(Object o) {
        if (Objects.isNull(o)) {
            return "null";
        }
        if (o instanceof String) {
            return "'" + o + "'";
        }
        if ((o instanceof Boolean) || (o instanceof Integer)) {
            return o.toString();
        }
        return "[complex value]";
    }
}
