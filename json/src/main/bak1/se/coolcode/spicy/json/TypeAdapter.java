package se.coolcode.spicy.json;

import java.lang.reflect.Field;

public interface TypeAdapter<T> {
    String getJsonValue(String key, String json);
    T getValue(Class<?> type, String key, String json);
}
