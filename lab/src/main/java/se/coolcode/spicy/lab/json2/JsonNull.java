package se.coolcode.spicy.lab.json2;

import java.lang.reflect.Type;

public class JsonNull implements JsonElement {

    public static JsonElement instance;

    @Override
    public String asJson() {
        return null;
    }

    @Override
    public <T> T as(Type type) {
        return null;
    }
}
