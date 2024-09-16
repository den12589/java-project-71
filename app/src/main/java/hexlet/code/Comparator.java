package hexlet.code;

import java.util.*;

public class Comparator {

    public static List<Map<String, Object>> compare(Map<String, Object> file1, Map<String, Object> file2) {
        var key = new TreeSet<String>();
        key.addAll(file1.keySet());
        key.addAll(file2.keySet());
        List<Map<String, Object>> result = new ArrayList<>();
        key.forEach(nextKey -> {
            Map<String, Object> map = new HashMap<>();
            map.put("NAME", nextKey);
            if (!file1.containsKey(nextKey)) {
                map.put("STATUS", "ADD");
                map.put("NEW VALUE", file2.get(nextKey));
            } else {
                if (!file2.containsKey(nextKey)) {
                    map.put("STATUS", "DELETED");
                    map.put("OLD VALUE", file1.get(nextKey));
                } else {
                    var firstGetObj = file1.get(nextKey);
                    var secondGetObj = file2.get(nextKey);
                    if (firstGetObj == null || secondGetObj == null) {
                        if (firstGetObj == null && secondGetObj == null) {
                            map.put("STATUS", "SAME");
                            map.put("OLD VALUE", null);
                        } else {
                            map.put("STATUS", "UPDATE");
                            map.put("OLD VALUE", firstGetObj);
                            map.put("NEW VALUE", secondGetObj);
                        }
                    } else {
                        if (firstGetObj.equals(secondGetObj)) {
                            map.put("STATUS", "SAME");
                            map.put("OLD VALUE", firstGetObj);
                        } else {
                            map.put("STATUS", "UPDATE");
                            map.put("OLD VALUE", firstGetObj);
                            map.put("NEW VALUE", secondGetObj);
                        }
                    }
                }
            }
            result.add(map);
        });
        return result;
    }
}
