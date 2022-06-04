package se.coolcode.spicy.json;

public class JsonException extends RuntimeException {

    public JsonException(Exception e) {
        super(e);
    }
}
