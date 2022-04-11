package se.coolcode.spicy.lab.json2;

public class JsonString implements JsonElement {

    private JsonValue value;

    public JsonString(JsonValue value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
