package se.coolcode.spicy.lab.json2;

import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

public class JsonArray implements JsonElement {

    private JsonValue value;
    private List<JsonValue> values;

    public JsonArray(JsonValue value) {
        this.value = value;
        values = new JsonReader(new StringReader(value.value())).getValues();
    }

    public JsonElement get(int index) {
        JsonValue jsonValue = values.get(index);
        return jsonValue.toJsonElement();
    }

    @Override
    public String toString() {
        return values.stream()
                .map(JsonValue::toString)
                .collect(Collectors.joining(",", "[", "]"));
    }
}
