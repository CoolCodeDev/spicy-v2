package se.coolcode.spicy.json;

import java.util.Arrays;
import java.util.function.Function;

enum JsonType {
    OBJECT(JsonType::isBeginningObject),
    ARRAY(JsonType::isBeginningArray),
    BOOLEAN(JsonType::isBeginningBoolean),
    NUMBER(Character::isDigit),
    STRING(JsonType::isBeginningString),
    NULL(JsonType::isBeginningNull);

    private final Function<Character, Boolean> isBeginning;

    JsonType(Function<Character, Boolean> isBeginning) {
        this.isBeginning = isBeginning;
    }

    public static JsonType getFrom(String data) {
        return data.codePoints()
                .mapToObj(codepoint -> (char) codepoint)
                .filter(JsonType::matchesAnyBeginning)
                .map(JsonType::getFromBeginning)
                .findFirst()
                .orElse(JsonType.NULL);
    }

    private static boolean matchesAnyBeginning(char c) {
        return Arrays.stream(JsonType.values())
                .anyMatch(jsonType -> jsonType.isBeginning.apply(c));
    }

    private static JsonType getFromBeginning(char c) {
        return Arrays.stream(JsonType.values())
                .filter(jsonType -> jsonType.isBeginning.apply(c))
                .findFirst()
                .orElse(JsonType.NULL);
    }

    private static boolean isBeginningObject(char c) {
        return '{' == c;
    }

    private static boolean isBeginningArray(char c) {
        return '[' == c;
    }

    private static boolean isBeginningBoolean(char c) {
        return 't' == c || 'T' == c || 'f' == c || 'F' == c;
    }

    private static boolean isBeginningString(char c) {
        return '"' == c;
    }

    private static boolean isBeginningNull(char c) {
        return 'n' == c || 'N' == c || 'u' == c || 'U' == c;
    }
}
