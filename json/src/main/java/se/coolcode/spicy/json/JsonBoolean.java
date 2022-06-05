package se.coolcode.spicy.json;

public class JsonBoolean implements JsonElement {

    public static TypeBuilder builder() {
        return null;
    }

    private static class JsonBooleanTypeBuilder implements TypeBuilder {

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
