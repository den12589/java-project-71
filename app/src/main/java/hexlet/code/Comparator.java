package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.LinkedHashMap;
import java.util.Objects;

import static hexlet.code.DifferKey.NAME;
import static hexlet.code.DifferKey.STATUS;
import static hexlet.code.DifferKey.OLD_VALUE;
import static hexlet.code.DifferKey.NEW_VALUE;
import static hexlet.code.Status.ADD;
import static hexlet.code.Status.SAME;
import static hexlet.code.Status.REMOVED;
import static hexlet.code.Status.UPDATED;

public class Comparator {

    public static List<Map<DifferKey, Object>> compare(Map<String, Object> file1, Map<String, Object> file2) {
        List<Map<DifferKey, Object>> result = new ArrayList<>();
        var key = new TreeSet<String>();
        key.addAll(file1.keySet());
        key.addAll(file2.keySet());
        key.forEach(nextKey -> {
            Map<DifferKey, Object> map = new LinkedHashMap<>();
            map.put(NAME, nextKey);
            if (!file1.containsKey(nextKey)) {
                map.put(STATUS, ADD);
                map.put(NEW_VALUE, file2.get(nextKey));
            } else if (!file2.containsKey(nextKey)) {
                map.put(STATUS, REMOVED);
                map.put(OLD_VALUE, file1.get(nextKey));
            } else if (Objects.equals(file1.get(nextKey), file2.get(nextKey))) {
                map.put(STATUS, SAME);
                map.put(OLD_VALUE, file1.get(nextKey));
            } else {
                map.put(STATUS, UPDATED);
                map.put(OLD_VALUE, file1.get(nextKey));
                map.put(NEW_VALUE, file2.get(nextKey));
            }
            result.add(map);
        });
        return result;
    }
}
