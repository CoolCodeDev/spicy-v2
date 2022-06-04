package se.coolcode.spicy.lab.json2;

import java.io.StringReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class JsonArray implements JsonElement {

    private final List<JsonValue> values;

    public JsonArray(JsonValue jsonValue) {
        this(jsonValue.value());
    }

    public JsonArray(String json) {
        values = new JsonReader(new StringReader(json)).getValues();
    }

    @Override
    public JsonArray asJsonArray() {
        return this;
    }

    public JsonElement get(int index) {
        JsonValue jsonValue = values.get(index);
        return jsonValue.toJsonElement();
    }

    @Override
    public String asJson() {
        return values.stream()
                .map(JsonValue::toString)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public List<JsonElement> asList() {
        return values.stream()
                .map(JsonValue::toJsonElement)
                .collect(Collectors.toList());
    }

    public <T extends Collection<JsonElement>> T asCollection(T collection) {
        return values.stream()
                .map(JsonValue::toJsonElement)
                .collect(Collectors.toCollection(() -> collection));
    }

    @Override
    public <T> T as(Type type) {
        if (type instanceof ParameterizedType parameterizedType) {
            Collection<Object> collection = getCollectionType(parameterizedType);
            Type genericType = parameterizedType.getActualTypeArguments()[0];
            collection.addAll(values.stream()
                    .map(JsonValue::toJsonElement)
                    .map(element -> element.as(genericType))
                    .collect(Collectors.toList()));
            return (T) collection;
        }
        throw new JsonException(String.format("Could not create collection from type %s.", type));
    }

    private Collection<Object> getCollectionType(ParameterizedType parameterizedType) {
        try {
            Class<?> clazz = (Class<?>) parameterizedType.getRawType();
            if (clazz.isInterface()) {
                if (clazz.equals(List.class)) {
                    return new ArrayList<>();
                } else if (clazz.equals(Queue.class)) {
                    return new ArrayDeque<>();
                } else if (clazz.equals(Set.class)) {
                    return new HashSet<>();
                }
                throw new JsonException(String.format("Field type %s is not supported as a collection type.", clazz));
            } else {
                return (Collection<Object>) clazz.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            throw new JsonException(String.format("Could not instantiate collection of type %s.", parameterizedType.getRawType()));
        }
    }

}
