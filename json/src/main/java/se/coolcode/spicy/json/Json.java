package se.coolcode.spicy.json;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Json {

    /**
     * Matches a key value pair and groups the key and the value to referable groups. <br>
     * Key is found in first group, value in the second group. <br>
     * A regex group is denoted with ().
     */
    public static final String JSON_KEY_VALUE_REGEX = "\"(.*?)\": \"(.*?)\"";

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

    public static Map<String, String> toMap(String json) {
        Pattern pattern = Pattern.compile(JSON_KEY_VALUE_REGEX, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(json);
        return matcher.results()
                .collect(Collectors.toMap(
                        match -> match.group(1),
                        match -> match.group(2)));

    }

}
