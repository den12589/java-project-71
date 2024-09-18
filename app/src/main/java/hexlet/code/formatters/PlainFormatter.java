package hexlet.code.formatters;

import java.util.*;

public class PlainFormatter {
    public static String format(List<Map<String, Object>> compareList) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (Map<String, Object> map : compareList) {
            StringBuilder next = new StringBuilder();
            String begin = "Property '" + map.get("NAME").toString() + "' was ";
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
                        if (isRewedType(value)) {
                            next.append("[complex value]");
                        } else if(!(value instanceof String)){
                            next.append(value);
                        } else {
                            next.append("'").append(value).append("'");
                        }
                    }
                    stringJoiner.add(next);
                    break;
                case "UPDATE":
                    next.append(begin);
                    next.append("updated. From ");
                    Object oldValue = map.get("OLD VALUE");
                    Object newValue = map.get("NEW VALUE");
                    if (oldValue == null) {
                        next.append("null");
                    } else if (isRewedType(oldValue)) {
                        next.append("[complex value]");
                    } else if(!(oldValue instanceof String)) {
                        next.append(oldValue);
                    } else {
                        next.append("'").append(oldValue).append("'");
                    }
                    next.append(" to ");
                    if (newValue == null) {
                        next.append("null");
                    } else if (isRewedType(newValue)) {
                        next.append("[complex value]");
                    } else if(!(newValue instanceof String)) {
                        next.append(newValue);
                    } else {
                        next.append("'").append(newValue).append("'");
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

    private static boolean isRewedType(Object o) {
        return (!(o instanceof Integer) && !(o instanceof String) && !(o instanceof Boolean));
    }
}
