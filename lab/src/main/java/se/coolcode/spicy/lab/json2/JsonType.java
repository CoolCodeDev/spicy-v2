package se.coolcode.spicy.lab.json2;

public enum JsonType {
    STRING('"', '"'),
    ARRAY('[', ']');

    private final char begin;
    private final char end;

    JsonType(char begin, char end) {
        this.begin = begin;
        this.end = end;
    }

    public static JsonType getType(char c) {
        if (STRING.isBegin(c)) {
            return STRING;
        } else if (ARRAY.isBegin(c)) {
            return ARRAY;
        }
        return null;
    }

    public char getBegin() {
        return begin;
    }

    public char getEnd() {
        return end;
    }

    public boolean isBegin(char c) {
        return begin == c;
    }

    public boolean isEnd(char c) {
        return end == c;
    }
}
