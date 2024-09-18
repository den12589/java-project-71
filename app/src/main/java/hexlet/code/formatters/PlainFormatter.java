package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class PlainFormatter {
    public static String format(List<Map<String, Object>> compareList) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (Map<String, Object> map : compareList) {
            StringBuilder next = new StringBuilder();
            String begin = "Property '" + map.get("NAME").toString() + "' was ";
            String status = map.get("STATUS").toString();

            switch (status) {
                case "SAME":
                    break;
                case "REMOVED":
                    next.append(begin).append("removed");
                    stringJoiner.add(next);
                    break;
                case "ADD":
                    next.append(begin).append("added with value: ");
                    next.append(createValueString(map.get("NEW VALUE")));
                    stringJoiner.add(next);
                    break;
                case "UPDATE":
                    next.append(begin).append("updated. From ");
                    next.append(createValueString(map.get("OLD VALUE")));
                    next.append(" to ");
                    next.append(createValueString(map.get("NEW VALUE")));
                    stringJoiner.add(next);
                    break;
                default:
                    throw new RuntimeException("Can't read status at PlainFormat");
            }
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
