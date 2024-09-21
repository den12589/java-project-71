package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DifferKey;

import java.util.List;
import java.util.Map;

public class JsonFormatter {
    public static String format(List<Map<DifferKey, Object>> differences) {
        String result = "";
        try {
            result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(differences);
        } catch (JsonProcessingException e) {
            System.out.println(e.getClass().getName()
                    + " : " + e.getOriginalMessage());
        }
        return result;
    }
}
