package se.coolcode.spicy.lab.json2;

import java.io.StringReader;
import java.util.Map;

public class Json
{
    public static JsonElement fromJson(String json) {
        json = json.trim();
        JsonType type = JsonType.getType(json.charAt(0));
        return new JsonValue(type, json).toJsonElement();
    }
}
