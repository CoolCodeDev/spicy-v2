package se.coolcode.spicy.json;

public class JsonString implements JsonElement {

    private final String jsonValue;

    private JsonString(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public static TypeBuilder builder() {
        return new JsonStringTypeBuilder();
    }

    private static class JsonStringTypeBuilder implements TypeBuilder {
        private final StringBuilder stringBuilder = new StringBuilder();
        private boolean appendable = true;

        @Override
        public boolean isAppendable() {
            return appendable;
        }

        @Override
        public void append(char c) {
            if ((JsonType.STRING.isBeginning(c) && stringBuilder.isEmpty()) || !stringBuilder.isEmpty()) {
                stringBuilder.append(c);
            } else if (JsonType.STRING.isEnding(c) && !stringBuilder.isEmpty()) {
                stringBuilder.append(c);
                appendable = false;
            }
        }

        @Override
        public JsonElement build() {
            String jsonValue = stringBuilder.toString();
            if (jsonValue.startsWith("\"") && jsonValue.endsWith("\"")) {
                return new JsonString(jsonValue);
            }
            throw new JsonException("Failed to create JsonString due to invalid json value.");
        }
    }
}
