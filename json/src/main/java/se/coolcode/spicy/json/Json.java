package se.coolcode.spicy.json;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class Json {

    public static String toJson(Map<String, String> map) {
        return map == null ? null : map.entrySet().stream()
                .map(entry -> String.format("\"%s\": \"%s\"", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", ", "{", "}"));
    }

    public static String toJsonArray(Map<String, Collection<String>> map) {
        return map == null ? null : map.entrySet().stream()
                .map(entry -> String.format("\"%s\": %s", entry.getKey(), toJsonArray(entry.getValue())))
                .collect(Collectors.joining(", ", "{", "}"));
    }

    private static String toJsonArray(Collection<String> collection) {
        return collection == null ? null : collection.stream()
                .map(string -> String.format("\"%s\"", string))
                .collect(Collectors.joining(", ", "[", "]"));
    }

}
