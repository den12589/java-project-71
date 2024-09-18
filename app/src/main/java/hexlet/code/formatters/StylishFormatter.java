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
            var status = map.get("STATUS");
            String nextObj;
            switch (status.toString()) {
                case "ADD":
                    nextObj = Objects.isNull(map.get("NEW VALUE")) ? "null" : map.get("NEW VALUE").toString();
                    result.add(" + " + map.get("NAME") + ": " + nextObj);
                    break;
                case "REMOVED":
                    nextObj = Objects.isNull(map.get("OLD VALUE")) ? "null" : map.get("OLD VALUE").toString();
                    result.add(" - " + map.get("NAME") + ": " + nextObj);
                    break;
                case "SAME":
                    nextObj = Objects.isNull(map.get("OLD VALUE")) ? "null" : map.get("OLD VALUE").toString();
                    result.add("   " + map.get("NAME") + ": " + nextObj);
                    break;
                case "UPDATE":
                    nextObj = Objects.isNull(map.get("OLD VALUE")) ? "null" : map.get("OLD VALUE").toString();
                    result.add(" - " + map.get("NAME") + ": " + nextObj);
                    nextObj = Objects.isNull(map.get("NEW VALUE")) ? "null" : map.get("NEW VALUE").toString();
                    result.add(" + " + map.get("NAME") + ": " + nextObj);
                    break;
                default:
                    throw new RuntimeException("Can't read status at StylishFormat");
            }
        }
        result.add("}");
        return result.toString();
    }
}
