package se.coolcode.spicy.json;

import se.coolcode.spicy.json.reader.JsonReader;

public class Json {
    public JsonObject fromJson(String data) {
        JsonReader jsonReader = new JsonReader(data);
        return new JsonObject(jsonReader);
    }
}
