package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class PlainFormatter {
    public static String format(List<Map<String, Object>> compareList) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (Map<String, Object> map : compareList) {
            StringBuilder next = new StringBuilder("Property " + "'" + map.get("NAME").toString() + "' was ");
            String status = map.get("STATUS").toString();

            switch (status) {
                case "DELETED":
                    next.append("removed");
                    break;
                case "ADD":
                    next.append("added with value: ").append(map.get("NEW VALUE") != null ? map.get("NEW VALUE").toString() : "null");
                    break;
                case "UPDATE":
                    next.append("updated. From ");
                    Object oldValue = map.get("OLD VALUE");
                    if (oldValue == null) {
                        next.append("null to ");
                    } else if (!oldValue.getClass().isPrimitive()) {
                        next.append("[complex value] to ");
                    } else {
                        next.append(oldValue).append(" to");
                    }
                    Object newValue = map.get("NEW VALUE");
                    if (newValue == null) {
                        next.append("null");
                    } else if (!newValue.getClass().isPrimitive()) {
                        next.append("[complex value]");
                    } else {
                        next.append(newValue);
                    }
                    break;
                case "SAME":
                    break;
                default:
                    throw new RuntimeException();
            }

            stringJoiner.add(next);
        }
        return stringJoiner.toString();
    }
}
