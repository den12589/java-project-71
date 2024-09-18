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
            String status = map.get("STATUS").toString();
            String oldValue = Objects.isNull(map.get("OLD VALUE")) ? "null" : map.get("OLD VALUE").toString();
            String newValue = Objects.isNull(map.get("NEW VALUE")) ? "null" : map.get("NEW VALUE").toString();
            switch (status) {
                case "ADD":
                    result.add(" + " + map.get("NAME") + ": " + newValue);
                    break;
                case "REMOVED":
                    result.add(" - " + map.get("NAME") + ": " + oldValue);
                    break;
                case "SAME":
                    result.add("   " + map.get("NAME") + ": " + oldValue);
                    break;
                case "UPDATE":
                    result.add(" - " + map.get("NAME") + ": " + oldValue);
                    result.add(" + " + map.get("NAME") + ": " + newValue);
                    break;
                default: throw new RuntimeException("Status isn't correct");
            }
        }
        result.add("}");
        return result.toString();
    }
}
