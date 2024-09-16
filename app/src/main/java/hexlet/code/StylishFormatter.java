package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class StylishFormatter {
    public static String format(List<Map<String, Object>> compareList) {
        StringJoiner result = new StringJoiner("\n");
        result.add("{");
        for (Map<String, Object> map: compareList) {
            var status = map.get("STATUS");
            switch (status.toString()) {
                case "ADD": result.add("+ " + map.get("NAME") + ": " + map.get("NEW VALUE").toString());
                case "DELETED": result.add("- " + map.get("NAME") + ": " + map.get("OLD VALUE").toString());
                case "SAME": result.add("  " + map.get("NAME") + ": " + map.get("OLD VALUE").toString());
                case "UPDATE":
                    result.add("- " + map.get("NAME") + ": " + map.get("OLD VALUE").toString());
                    result.add("+ " + map.get("NAME") + ": " + map.get("NEW VALUE").toString());
                default: throw new RuntimeException("Can't read status at StylishFormat");
            }
        }
        result.add("}");
        return result.toString();
    }
}
