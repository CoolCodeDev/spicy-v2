package se.coolcode.spicy.lab.json2;

import java.lang.reflect.Type;

public interface JsonElement {

    default JsonObject asJsonObject() {
        throw new JsonException("Value is not an object.");
    }

    default JsonString asJsonString() {
        throw new JsonException("Value is not a string.");
    }

    default JsonArray asJsonArray() {
        throw new JsonException("Value is not an array.");
    }

    String asJson();
    <T> T as(Type type);

}
