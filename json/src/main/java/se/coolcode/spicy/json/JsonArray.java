package se.coolcode.spicy.json;

public class JsonArray implements JsonElement {

    public static TypeBuilder builder() {
        return null;
    }

    private static class JsonArrayTypeBuilder implements TypeBuilder {

        @Override
        public boolean isAppendable() {
            return false;
        }

        @Override
        public void append(char c) {

        }

        @Override
        public JsonElement build() {
            return null;
        }
    }
}
