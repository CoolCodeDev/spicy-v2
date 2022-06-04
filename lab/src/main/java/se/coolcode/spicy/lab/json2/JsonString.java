package se.coolcode.spicy.lab.json2;

import java.lang.reflect.Type;

public class JsonString implements JsonElement {

    private JsonValue value;

    public JsonString(JsonValue value) {
        this.value = value;
    }

    @Override
    public JsonString asJsonString() {
        return this;
    }

    @Override
    public String asJson() {
        return value.toString();
    }

    public String asString() {
        return value.value();
    }

    @Override
    public <T> T as(Type type) {
        if (type.equals(String.class)) {
            return (T) asString();
        } else {
            String message = String.format("String cannot be converted to type %s.", type);
            throw new JsonException(message);
        }
    }
}
