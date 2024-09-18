package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class StylishFormatter {
    public static String format(List<Map<String, Object>> compareList) {
        StringJoiner result = new StringJoiner("\n");
        result.add("{");
        for (Map<String, Object> map : compareList) {
            var name = map.get("NAME");
            var status = map.get("STATUS");
            if (status.equals("REMOVED") || status.equals("UPDATE") || status.equals("SAME")) {
                String oldValue = Objects.isNull(map.get("OLD VALUE")) ? "null" : map.get("OLD VALUE").toString();
                if (status.equals("SAME")) {
                    result.add("   " + name + ": " + oldValue);
                } else {
                    result.add(" - " + name + ": " + oldValue);
                }
            }
            if (status.equals("ADD") || status.equals("UPDATE")) {
                String temp = Objects.isNull(map.get("NEW VALUE")) ? "null" : map.get("NEW VALUE").toString();
                result.add(" + " + name + ": " + temp);
            }
        }
        result.add("}");
        return result.toString();
    }
}
