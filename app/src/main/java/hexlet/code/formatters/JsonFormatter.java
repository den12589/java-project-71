package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class JsonFormatter {
    public static String format(List<Map<String, Object>> compareList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("{");
        int count = 1;
        for (Map map : compareList) {
            stringJoiner.add("  \"change_" + count + "\": " + mapper.writeValueAsString(map) + ",");
            count++;
        }
        String result = stringJoiner.toString().substring(0, stringJoiner.length() - 1) + "\n}";
        return result;
    }
}
