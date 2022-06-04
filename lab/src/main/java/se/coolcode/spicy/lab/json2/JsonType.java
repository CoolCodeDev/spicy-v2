package se.coolcode.spicy.lab.json2;

public enum JsonType {
    STRING('"', '"'),
    ARRAY('[', ']'),
    OBJECT('{', '}'),
    NULL((char) -1, (char) -1);

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
        } else if (OBJECT.isBegin(c)) {
            return OBJECT;
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
