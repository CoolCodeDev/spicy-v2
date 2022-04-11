package se.coolcode.spicy.lab.json2;

import java.util.Map;

public class JsonObject implements JsonElement {

    private final Map<String, JsonValue> values;

    public JsonObject(Map<String, JsonValue> values) {
        this.values = values;
    }

    public JsonElement get(String key) {
        JsonValue jsonValue = values.get(key);
        return jsonValue.toJsonElement();
    }

}
