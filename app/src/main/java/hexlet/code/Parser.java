package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String content, String typeFile) throws JsonProcessingException {
        return switch (typeFile) {
            case "json" -> new ObjectMapper().readValue(content, Map.class);
            case "yaml" -> new ObjectMapper(new YAMLFactory()).readValue(content, Map.class);
            default -> throw new RuntimeException("file_type isn't supported");
        };
    }
}


