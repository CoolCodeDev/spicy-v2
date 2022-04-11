package se.coolcode.spicy.lab.json2;

public record JsonValue(JsonType type, String value) {

    @Override
    public String toString() {
        return String.format("%s%s%s", type.getBegin(), value, type.getEnd());
    }

    public JsonElement toJsonElement() {
        return switch (type) {
            case STRING -> new JsonString(this);
            case ARRAY -> new JsonArray(this);
        };
    }
}
