package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class PlainFormatter {
    public static String format(List<Map<String, Object>> compareList) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (Map<String, Object> map : compareList) {
            String status = map.get("STATUS").toString();
            if (status.equals("SAME")) {
                continue;
            }
            StringBuilder next = new StringBuilder();
            next.append("Property '").append(map.get("NAME").toString()).append("' was ");
            switch (status) {
                case "REMOVED" -> next.append("removed");
                case "ADD" -> next.append("added with value: ")
                        .append(createValueString(map.get("NEW VALUE")));
                case "UPDATE" -> next.append("updated. From ")
                        .append(createValueString(map.get("OLD VALUE")))
                        .append(" to ")
                        .append(createValueString(map.get("NEW VALUE")));
                default -> throw new RuntimeException("Status isn't correct");
            }
            stringJoiner.add(next);
        }
        return stringJoiner.toString();
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
