package se.coolcode.spicy.lab.json2;

public class JsonException extends RuntimeException {

    public JsonException(String message) {
        super(message);
    }

    public JsonException(String message, Exception e) {
        super(message, e);
    }
}
