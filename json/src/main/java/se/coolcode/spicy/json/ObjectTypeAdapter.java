package se.coolcode.spicy.json;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectTypeAdapter implements TypeAdapter<Object> {

    private final String VALUE_REGEX_JSON = "\"%s\": (\\{.*?\\})[,]?";
    private final String VALUE_REGEX = "\"%s\": {(.*?)}";
    private final String VALUE_INIT = "{(.*?)}";

    @Override
    public String getJsonValue(String key, String json) {
        String regex = String.format(VALUE_REGEX_JSON, key);
        Matcher matcher = Pattern.compile(regex).matcher(json);
        return matcher.find() ? matcher.group(1) : null;
    }

    @Override
    /**
     * json = {"string": "stringvalue"}
     * json = {"another": {"one": "hello"}}
     */
    public Object getValue(Class<?> type, String key, String json) {
        try {
            Object object = type.getDeclaredConstructor().newInstance();
            while(!json.equals("{}")) {
                String nextKey = getNextKey(json);
                Field field = object.getClass().getDeclaredField(nextKey);
                TypeAdapter<?> adapter = TypeAdapters.getAdapter(field.getType());
                String jsonValue = adapter.getJsonValue(nextKey, json);
                Object value = adapter.getValue(field.getType(), nextKey, jsonValue);
                boolean access = field.canAccess(object);
                field.setAccessible(true);
                field.set(object, value);
                field.setAccessible(access);
                String keyValue = Pattern.quote(String.format("\"%s\": %s", nextKey, jsonValue));
                json = json.replaceFirst(keyValue+"[,]?[ ]?", "");
            }
            return object;
        } catch (Exception e) {
            throw new JsonException(e);
        }
    }

    private static String getNextKey(String json) {
        Matcher matcher = Pattern.compile("\"(.*?)\":").matcher(json);
        matcher.find();
        String fieldName = matcher.group(1);
        return fieldName;
    }

    private static <T> Field getDeclaredField(T object, String fieldName) throws NoSuchFieldException {
        return object.getClass().getDeclaredField(fieldName);
    }
}
