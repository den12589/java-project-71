package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class PlainFormatter {
    public static String format(List<Map<String, Object>> compareList) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (Map<String, Object> map : compareList) {
            StringBuilder next = new StringBuilder();
            String begin = "Property  '" + map.get("NAME").toString() + "' was ";
            String status = map.get("STATUS").toString();

            switch (status) {
                case "DELETED":
                    next.append(begin);
                    next.append("removed");
                    stringJoiner.add(next);
                    break;
                case "ADD":
                    next.append(begin);
                    next.append("added with value: ");
                    Object value = map.get("NEW VALUE");
                    if (value == null) {
                        next.append("null");
                    } else {
                        if (value.getClass().isArray()) {
                            next.append("[complex value]");
                        } else {
                            next.append(value);
                        }
                    }
                    stringJoiner.add(next);
                    break;
                case "UPDATE":
                    next.append(begin);
                    next.append("updated. From ");
                    Object oldValue = map.get("OLD VALUE");
                    if (oldValue == null) {
                        next.append("null to ");
                    } else if (oldValue.getClass().isArray()) {
                        next.append("[complex value] to ");
                    } else {
                        next.append(oldValue).append(" to ");
                    }
                    Object newValue = map.get("NEW VALUE");
                    if (newValue == null) {
                        next.append("null");
                    } else if (newValue.getClass().isArray()) {
                        next.append("[complex value]");
                    } else {
                        next.append(newValue);
                    }
                    stringJoiner.add(next);
                    break;
                case "SAME":
                    break;
                default:
                    throw new RuntimeException();
            }
        }
        return stringJoiner.toString();
    }
}
