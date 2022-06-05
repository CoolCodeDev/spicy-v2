package se.coolcode.spicy.json;

class TypeBuilders {

    static TypeBuilder getFrom(String data) {
        JsonType jsonType = JsonType.getFrom(data);
        return switch (jsonType) {
            case OBJECT -> JsonObject.builder();
            case ARRAY -> JsonArray.builder();
            case BOOLEAN -> JsonBoolean.builder();
            case NUMBER -> JsonNumber.builder();
            case STRING -> JsonString.builder();
            default -> JsonNull.builder();
        };
    }
}
