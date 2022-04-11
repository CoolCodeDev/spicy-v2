package se.coolcode.spicy.lab.json2;

import java.io.StringReader;
import java.util.Map;

public class Json
{
    public static JsonObject fromJson(String json) {
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        Map<String, JsonValue> values = jsonReader.getKeyValues();
        return new JsonObject(values);
    }
}
