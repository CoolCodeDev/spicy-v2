package se.coolcode.spicy.json;

public enum JsonType {
    Object('{', '}');

    private final char begin;
    private final char end;

    JsonType(char begin, char end) {
        this.begin = begin;
        this.end = end;
    }

    public static boolean isObject(String data) {
        return data != null && !data.isEmpty() && data.trim().charAt(0) == Object.begin;
    }
}
