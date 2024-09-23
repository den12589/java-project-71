package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.LinkedHashMap;
import java.util.Objects;

public class Comparator {

    public static List<Map<DifferKey, Object>> compare(Map<String, Object> file1, Map<String, Object> file2) {
        List<Map<DifferKey, Object>> result = new ArrayList<>();
        var key = new TreeSet<String>();
        key.addAll(file1.keySet());
        key.addAll(file2.keySet());
        key.forEach(nextKey -> {
            Map<DifferKey, Object> map = new LinkedHashMap<>();
            map.put(DifferKey.NAME, nextKey);
            if (!file1.containsKey(nextKey)) {
                map.put(DifferKey.STATUS, Status.ADD);
                map.put(DifferKey.NEW_VALUE, file2.get(nextKey));
            } else if (!file2.containsKey(nextKey)) {
                map.put(DifferKey.STATUS, Status.REMOVED);
                map.put(DifferKey.OLD_VALUE, file1.get(nextKey));
            } else if (Objects.equals(file1.get(nextKey), file2.get(nextKey))) {
                map.put(DifferKey.STATUS, Status.SAME);
                map.put(DifferKey.OLD_VALUE, file1.get(nextKey));
            } else {
                map.put(DifferKey.STATUS, Status.UPDATED);
                map.put(DifferKey.OLD_VALUE, file1.get(nextKey));
                map.put(DifferKey.NEW_VALUE, file2.get(nextKey));
            }
            result.add(map);
        });
        return result;
    }
}
