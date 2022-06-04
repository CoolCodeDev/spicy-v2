package se.coolcode.spicy.lab.json2;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonObject implements JsonElement {

    private Map<String, JsonValue> values;

    public JsonObject(JsonValue jsonValue) {
        this(jsonValue.value());
    }

    public JsonObject(String json) {
        json = json.trim();
        json = json.substring(1, json.length() - 1);
        values = new JsonReader(new StringReader(json)).getKeyValues();
    }

    @Override
    public JsonObject asJsonObject() {
        return this;
    }

    public JsonElement get(String key) {
        JsonValue jsonValue = values.get(key);
        return jsonValue.toJsonElement();
    }

    @Override
    public String asJson() {
        return values.entrySet().stream()
                .map(entry -> String.format("\"%s\": %s", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", ", "{", "}"));
    }

    @Override
    public <T> T as(Type type) {
        try {
            Class<T> clazz = (Class<T>) type;
            T obj = (T) clazz.getDeclaredConstructor().newInstance();
            Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
                JsonValue jsonValue = values.getOrDefault(field.getName(), JsonValue.NULL);
                try {
                    if (jsonValue.type() != JsonType.NULL) {
                        field.set(obj, jsonValue.toJsonElement().as(field.getGenericType()));
                    }
                } catch (Exception e) {
                    String message = String.format("Could not set value %s for field %s on type %s.", jsonValue.value(), field.getName(), clazz);
                    throw new JsonException(message, e);
                }
            });
            return obj;
        } catch (Exception e) {
            String message = String.format("Could not convert json to object of type %s.", type);
            throw new JsonException(message, e);
        }
    }

}
