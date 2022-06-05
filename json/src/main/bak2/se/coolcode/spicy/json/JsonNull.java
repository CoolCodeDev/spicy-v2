package se.coolcode.spicy.json;

public class JsonNull implements JsonElement {
    public static final JsonElement NULL = new JsonNull();

    private JsonNull() {}
}
