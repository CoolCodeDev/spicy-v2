package se.coolcode.spicy.json;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoubleTypeAdapter implements TypeAdapter<Double> {
    private final String VALUE_REGEX_JSON = "\"%s\": (\\d+.\\d+)[, }]?";
    private final String VALUE_REGEX = "(\\d+.\\d+)";

    @Override
    public String getJsonValue(String key, String json) {
        return extractValue(key, json, VALUE_REGEX_JSON);
    }

    @Override
    public Double getValue(Class<?> type, String key, String json) {
        String value = extractValue(key, json, VALUE_REGEX);
        return value == null ? null : Double.parseDouble(value);
    }

    private String extractValue(String key, String json, String pattern) {
        String regex = String.format(pattern, key);
        Matcher matcher = Pattern.compile(regex).matcher(json);
        return matcher.find() ? matcher.group(1) : null;
    }
}
