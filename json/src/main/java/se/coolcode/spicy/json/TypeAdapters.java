package se.coolcode.spicy.json;

import java.util.Map;

public class TypeAdapters {

    private static Map<Class<?>, TypeAdapter> typeAdapters = Map.of(
            String.class, new StringTypeAdapter(),
            Object.class, new ObjectTypeAdapter(),
            Double.class, new DoubleTypeAdapter(),
            Float.class, new FloatTypeAdapter()
            );

    public static TypeAdapter getAdapter(Class<?> type) {
        return typeAdapters.getOrDefault(type, typeAdapters.get(Object.class));
    }
}
