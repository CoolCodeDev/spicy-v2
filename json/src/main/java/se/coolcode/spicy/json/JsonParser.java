package se.coolcode.spicy.json;

import java.io.StringReader;

class JsonParser {

    static JsonElement fromJson(String data) {
        try (StringReader reader = new StringReader(data)) {
            TypeBuilder typeBuilder = TypeBuilders.getFrom(data);
            int c = reader.read();
            while (typeBuilder.isAppendable() && c != -1) {
                typeBuilder.append((char) c);
                c = reader.read();
            }
            return typeBuilder.build();
        } catch (Exception e) {
            throw new JsonException("Could not parse json data.", e);
        }
    }
}
