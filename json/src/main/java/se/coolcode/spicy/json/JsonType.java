package se.coolcode.spicy.json;

import java.util.Arrays;
import java.util.function.Function;

enum JsonType {
    OBJECT(JsonType::isBeginningObject, JsonType::isEndingObject),
    ARRAY(JsonType::isBeginningArray, JsonType::isEndingArray),
    BOOLEAN(JsonType::isBeginningBoolean, JsonType::endingIsNotApplicable),
    NUMBER(Character::isDigit, JsonType::endingIsNotApplicable),
    STRING(JsonType::isBeginningString, JsonType::isEndingString),
    NULL(JsonType::isBeginningNull, JsonType::endingIsNotApplicable);

    private final Function<Character, Boolean> isBeginning;
    private final Function<Character, Boolean> isEnding;

    JsonType(Function<Character, Boolean> isBeginning, Function<Character, Boolean> isEnding) {
        this.isBeginning = isBeginning;
        this.isEnding = isEnding;
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

    private static boolean isEndingObject(char c) {
        return '}' == c;
    }

    private static boolean isBeginningArray(char c) {
        return '[' == c;
    }

    private static boolean isEndingArray(char c) {
        return ']' == c;
    }

    private static boolean isBeginningBoolean(char c) {
        return 't' == c || 'T' == c || 'f' == c || 'F' == c;
    }

    private static boolean isBeginningString(char c) {
        return '"' == c;
    }

    private static boolean isEndingString(char c) {
        return isBeginningString(c);
    }

    private static boolean isBeginningNull(char c) {
        return 'n' == c || 'N' == c || 'u' == c || 'U' == c;
    }

    private static boolean endingIsNotApplicable(char c) {
        throw new JsonException("Evaluating if character %s is end of json type is not applicable.");
    }

    public boolean isBeginning(char c) {
        return this.isBeginning.apply(c);
    }

    public boolean isEnding(char c) {
        return false;
    }
}
