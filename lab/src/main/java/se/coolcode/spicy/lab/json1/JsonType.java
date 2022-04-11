package se.coolcode.spicy.lab.json1;

enum JsonType {
    STRING('"', '"' ),
    OBJECT('{', '}' ),
    ARRAY('[', ']' ),
    BOOLEAN(Character.MIN_VALUE, Character.MAX_VALUE),
    NUMBER(Character.MIN_VALUE, Character.MAX_VALUE),
    INTEGER(Character.MIN_VALUE, Character.MAX_VALUE),
    DECIMAL(Character.MIN_VALUE, Character.MAX_VALUE),
    NULL(Character.MIN_VALUE, Character.MAX_VALUE);

    public final char begin;
    public final char end;

    JsonType(char begin, char end) {
        this.begin = begin;
        this.end = end;
    }

    public boolean isBegin(char data) {
        return switch (this) {
            case STRING, OBJECT, ARRAY -> this.begin == data;
            case INTEGER, DECIMAL, NUMBER -> Character.isDigit(data);
            case BOOLEAN -> isBeginBoolean(data);
            case NULL -> isBeginNull(data);
        };
    }

    private boolean isBeginNull(char data) {
        //null, undefined
        return data == 'n' || data == 'u';
    }

    private boolean isBeginBoolean(char data) {
        //true, false
        return data == 't' || data == 'f';
    }

    public boolean isEnd(char data) {
        return data == this.end;
    }
}
