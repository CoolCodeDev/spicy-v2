package se.coolcode.spicy.json.reader;

import se.coolcode.spicy.json.JsonElement;
import se.coolcode.spicy.json.JsonException;
import se.coolcode.spicy.json.JsonType;

import java.util.HashMap;
import java.util.Map;

public class JsonReader {

    private final String data;

    public JsonReader(String data) {
        this.data = data;
    }

    public Map<String, String> getKeyValueMap() {
        if (JsonType.isObject(data)) {
            var result = new HashMap<String, String>();
            var objectReader = new JsonObjectReader(data);
            while(objectReader.hasNextKey()) {
                String key = objectReader.getNextKey();
                String value = objectReader.getNextValue();
                result.put(key, value);
            }
            return result;
        }
        throw new JsonException("Data is not a json object.");
    }

    public JsonElement getAsJsonElement() {
        return null;
    }
}
