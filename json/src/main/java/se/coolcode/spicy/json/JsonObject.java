package se.coolcode.spicy.json;

import se.coolcode.spicy.json.reader.JsonReader;

import java.util.HashMap;
import java.util.Map;

public class JsonObject implements JsonElement {

    private final Map<String, String> data;
    private final Map<String, JsonElement> elementByKey;

    public JsonObject(JsonReader jsonReader) {
        data = jsonReader.getKeyValueMap();
        elementByKey = new HashMap<>();
    }

    public JsonElement get(String key) {
        JsonElement value = elementByKey.getOrDefault(key, JsonNull.NULL);
        if (value.isNull()) {
            String rawValue = data.get(key);
            JsonReader jsonReader = new JsonReader(rawValue);
            value = jsonReader.getAsJsonElement();
        }
        return value;
    }
}
