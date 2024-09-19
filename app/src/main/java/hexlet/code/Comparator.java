package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.LinkedHashMap;
import java.util.Objects;

public class Comparator {

    public static List<Map<String, Object>> compare(Map<String, Object> file1, Map<String, Object> file2) {
        var key = new TreeSet<String>();
        key.addAll(file1.keySet());
        key.addAll(file2.keySet());
        List<Map<String, Object>> result = new ArrayList<>();
        key.forEach(nextKey -> {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("NAME", nextKey);
            String status = getStatus(file1, file2, nextKey);
            map.put("STATUS", status);
            if (status.equals("REMOVED") || status.equals("UPDATE") || status.equals("SAME")) {
                map.put("OLD VALUE", file1.get(nextKey));
            }
            if (status.equals("ADD") || status.equals("UPDATE")) {
                map.put("NEW VALUE", file2.get(nextKey));
            }
            result.add(map);
        });
        return result;
    }

    private static String getStatus(Map<String, Object> file1, Map<String, Object> file2, String key) {
        if (!file1.containsKey(key)) {
            return "ADD";
        }
        if (!file2.containsKey(key)) {
            return "REMOVED";
        }
        var firstGetObj = Objects.isNull(file1.get(key)) ? "null" : file1.get(key);
        var secondGetObj = Objects.isNull(file2.get(key)) ? "null" : file2.get(key);
        if (firstGetObj.equals(secondGetObj)) {
            return "SAME";
        }
        return "UPDATE";
    }
}
