package se.coolcode.spicy.json;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTypeAdapter implements TypeAdapter<String> {
    private final String VALUE_REGEX_JSON = "\"%s\": (\".*?\")[, ]?";
    private final String VALUE_REGEX = "\"(.*?)\"";

    @Override
    public String getJsonValue(String key, String json) {
        return extractValue(key, json, VALUE_REGEX_JSON);
    }

    @Override
    public String getValue(Class<?> type, String key, String json) {
        return extractValue(key, json, "\"(.*?)\"");
    }

    private String extractValue(String key, String json, String pattern) {
        String regex = String.format(pattern, key);
        Matcher matcher = Pattern.compile(regex).matcher(json);
        return matcher.find() ? matcher.group(1) : null;
    }
}
