package se.coolcode.spicy.json;

public interface JsonElement {

    default boolean isNull() {
        return this == JsonNull.NULL;
    }
}
