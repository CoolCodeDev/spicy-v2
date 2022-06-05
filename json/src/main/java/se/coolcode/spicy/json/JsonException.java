package se.coolcode.spicy.json;

public class JsonException extends RuntimeException {

    public JsonException(String message) {
        super(message);
    }

    public JsonException(String message, Exception e) {
        super(message, e);
    }
}
